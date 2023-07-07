package com.orderit.app.service;

import com.orderit.app.dto.CompanyDTO;
import com.orderit.app.dto.CompanyUserDTO;
import com.orderit.app.dto.RegistrationDTO;
import com.orderit.app.mapper.CompanyMapper;
import com.orderit.app.mapper.CompanyUserMapper;
import com.orderit.common.entity.Company;
import com.orderit.common.entity.CompanyUser;
import com.orderit.common.enums.CompanyStatus;
import com.orderit.common.exception.ConflictRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RegisterService {
    private final CompanyService companyService;
    private final UserService userService;
    private final CompanyUserMapper companyUserMapper;
    private final CompanyMapper companyMapper;
    public Company registerCompanyAndCompanyUser(RegistrationDTO  registrationDTO) {
        CompanyDTO companyDTO = registrationDTO.getCompanyDTO();
        CompanyUserDTO companyUserDTO = registrationDTO.getCompanyUserDTO();
        Company newCompany = companyService.createCompany(companyMapper.toEntity(companyDTO),null);
        userService.registerCompanyOwnerForNewCompany(companyUserMapper.toEntity(companyUserDTO), newCompany);
        return newCompany;
    }

}
