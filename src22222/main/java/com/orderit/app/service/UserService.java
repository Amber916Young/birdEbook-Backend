package com.orderit.app.service;

import com.orderit.app.dto.CompanyUserProfileDTO;
import com.orderit.app.dto.UserProfileDTO;
import com.orderit.app.dto.email.ActivateEmailDTO;
import com.orderit.app.dto.email.CompanyRegistrationEmailDTO;
import com.orderit.common.entity.Company;
import com.orderit.common.entity.CompanyUser;
import com.orderit.common.enums.Role;
import com.orderit.common.enums.UserStatus;
import com.orderit.common.exception.ConflictRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import com.orderit.common.exception.NotFoundRequestException;
import com.orderit.common.repository.UserRepository;
import com.orderit.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final EmailSenderService emailSenderService;
    private final CompanyService companyService;

    public CompanyUser getUserById() {
        CompanyUser companyUser = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.User_Not_Found));
        return companyUser;
    }

    public CompanyUser getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.User_Not_Found));
    }

    public CompanyUser findCompanyUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.User_Not_Found));
    }

    public CompanyUser updateCompanyUser(UserProfileDTO userProfileDTO) {
        CompanyUser companyUser = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.User_Not_Found));
        companyUser.setFirstName(userProfileDTO.getFirstName());
        companyUser.setLastName(userProfileDTO.getLastName());
        companyUser.setContactNumber(userProfileDTO.getContactNumber());
        return userRepository.save(companyUser);
    }

    public void registerCompanyOwnerForNewCompany(CompanyUser companyUser, Company company) {
        // if already exists, throw error
        if (userRepository.existsByEmail(companyUser.getEmail())) {
            throw new ConflictRequestException(ErrorReasonCode.Duplicated_UserEmail);
        }

        companyUser.setRole(Role.ROLE_COMPANY_OWNER);
        companyUser.setCompanyId(company.getId());
        companyUser.setStatus(UserStatus.INVITED);
        companyUser.setPassword(encoder.encode("password"));
        CompanyUser newUser = userRepository.save(companyUser);

        CompanyRegistrationEmailDTO registrationEmailDTO = new CompanyRegistrationEmailDTO();
        registrationEmailDTO.setCompany(company);
        registrationEmailDTO.setCompanyUser(newUser);
        emailSenderService.sendCompanyRegisterEmail(registrationEmailDTO);
    }


    public CompanyUser createCompanyUser(CompanyUser companyUser) {
        return saveNewCompanyUser(companyUser, SecurityUtil.getCurrentUserCompanyId());
    }


    public CompanyUser adminActiveCompanyUser(String companyUserEmail) {
        CompanyUser companyUser = getUserByEmail(companyUserEmail);
        String email = companyUser.getEmail();
        String randomKey = RandomString.make(10);
        companyUser.setResetKey(randomKey);        // send email
        Company company = companyService.getCompanyById(companyUser.getCompanyId());
        CompanyUser newUser = userRepository.save(companyUser);
        ActivateEmailDTO activateEmail = new ActivateEmailDTO(newUser.getFirstName() + " " + newUser.getLastName(), email, company.getCompanyName());
        emailSenderService.sendActivateEmail(activateEmail, randomKey);
        return newUser;
    }

    public CompanyUser updateCompanyUserProfile(CompanyUserProfileDTO companyUserProfileDTO) {
        CompanyUser companyUser = findCompanyUserById(companyUserProfileDTO.getId());
        companyUser.setContactNumber(companyUserProfileDTO.getContactNumber());
        companyUser.setFirstName(companyUserProfileDTO.getFirstName());
        companyUser.setLastName(companyUserProfileDTO.getLastName());
        companyUser.setRole(companyUserProfileDTO.getRole());
        return userRepository.save(companyUser);
    }

    public void deleteUser(Long userId) {
        CompanyUser companyUser = findCompanyUserById(userId);
        if (companyUser.getRole().equals(Role.ROLE_COMPANY_OWNER)) {
            throw new ConflictRequestException(ErrorReasonCode.Cannot_Modify_Owner);
        }
        userRepository.deleteById(userId);
    }

    private CompanyUser saveNewCompanyUser(CompanyUser companyUser, long companyId) {
        String email = companyUser.getEmail();
        // if already exists, throw error
        if (userRepository.existsByEmail(email)) {
            throw new ConflictRequestException(ErrorReasonCode.Duplicated_Username);
        }

        companyUser.setCompanyId(companyId);
        companyUser.setStatus(UserStatus.INVITED);
        String randomKey = RandomString.make(10);
        companyUser.setPassword(encoder.encode("password"));
        companyUser.setResetKey(randomKey);        // send email
        Company company = companyService.getCompanyById(companyId);

        CompanyUser newUser = userRepository.save(companyUser);
        ActivateEmailDTO activateEmail = new ActivateEmailDTO(newUser.getFirstName() + " " + newUser.getLastName(), email, company.getCompanyName());
        emailSenderService.sendActivateEmail(activateEmail, randomKey);

        return newUser;
    }


    public List<CompanyUser> findCompanyUserByCompanyIdAndRole(Role role) {
        Long companyId = SecurityUtil.getCurrentUserCompanyId();
        return userRepository.findCompanyUserByCompanyIdAndRole(companyId, role);
    }
}
