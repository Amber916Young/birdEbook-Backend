package com.orderit.app.service;

import com.orderit.app.dto.CompanyProfileDTO;
import com.orderit.common.entity.Company;
import com.orderit.common.enums.CompanyStatus;
import com.orderit.common.exception.ConflictRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import com.orderit.common.exception.NotFoundRequestException;
import com.orderit.common.repository.CompanyRepository;
import com.orderit.common.utils.SecurityUtil;
import com.orderit.common.utils.email.EmailSender;
import com.orderit.common.utils.s3.S3FileManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanyService {
    private final S3FileManager s3FileManager;
    private final CompanyRepository companyRepository;
    private final EmailSender emailService;
    private final EmailSenderService emailSenderService;

    public Company getCompanyByEmail(String email) {
        return companyRepository.findByCompanyEmail(email).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Company_Not_Found));
    }

    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Company_Not_Found));
    }

    public Company getCompanyForCurrentUser() {
        Long companyId = SecurityUtil.getCurrentUserCompanyId();
        return getCompanyById(companyId);
    }

    public Company updateCompanyProfile(CompanyProfileDTO companyProfileDTO) {
        Company company = getCompanyForCurrentUser();
        if (company != null) {
            company.setContactNumber(companyProfileDTO.getContactNumber());
            company.setContactName(companyProfileDTO.getContactName());
            return companyRepository.save(company);
        }
        throw new NotFoundRequestException(ErrorReasonCode.Company_Not_Found);
    }


    @SneakyThrows
    public Company createCompany(Company company, MultipartFile companyLogo) {
        checkIfCompanyIsValid(company);
        company.setStatus(CompanyStatus.VERIFIED);

        if (companyLogo != null && !companyLogo.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String fileName = company.getCompanyName() + uuid + ".png";
            String companyLogoUrl = s3FileManager.uploadCompanyLogoImage(fileName, companyLogo);
            company.setCompanyLogoUrl(companyLogoUrl);
        }

        return companyRepository.save(company);
    }

    public void createCompanyEmailTemplate() {
        emailService.createCompanyEmailTemplate();
    }

    public void updateCompanyLogo(String url, Company company) {
        if (company != null) {
            company.setCompanyLogoUrl(url);
            companyRepository.save(company);
        } else {
            throw new NotFoundRequestException(ErrorReasonCode.Company_Not_Found);
        }
    }

    private void checkIfCompanyIsValid(Company company) {
        if (companyRepository.existsByCompanyEmail(company.getCompanyEmail())) {
            throw new ConflictRequestException(ErrorReasonCode.Duplicated_Company_Email);
        } else if (companyRepository.existsByCompanyName(company.getCompanyName())) {
            throw new ConflictRequestException(ErrorReasonCode.Duplicated_Company_Name);
        }
    }
}
