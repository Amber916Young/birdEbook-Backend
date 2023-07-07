package com.orderit.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderit.app.dto.CompanyDTO;
import com.orderit.app.dto.CompanyUserDTO;
import com.orderit.app.mapper.CompanyMapper;
import com.orderit.app.mapper.CompanyUserMapper;
import com.orderit.app.service.CompanyService;
import com.orderit.app.service.UserService;
import com.orderit.common.entity.Company;
import com.orderit.common.entity.CompanyUser;
import com.orderit.common.exception.BadRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final CompanyService companyService;
    private final UserService userService;
    private final CompanyMapper companyMapper;
    private final CompanyUserMapper companyUserMapper;


    @PostMapping(value = "/companies")
    @PreAuthorize("hasAuthority('ROLE_INTERNAL_ADMIN')")
    public ResponseEntity<Company> registerCompany(@RequestParam("companyName") String companyName,
                                                   @RequestParam("companyEmail") String companyEmail,
                                                   @RequestParam("file") MultipartFile companyLogo,
                                                   @RequestParam("contactNumber") String contactNumber,
                                                   @RequestParam("contactName") String contactName,
                                                   @RequestParam("address") String address) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyName(companyName);
        companyDTO.setCompanyEmail(companyEmail);
        companyDTO.setContactNumber(contactNumber);
        companyDTO.setContactName(contactName);

        JSONObject jsonObject = new JSONObject(address);
        if (jsonObject.has("addressLine1")) {
            companyDTO.setAddressLine1(jsonObject.getString("addressLine1"));
        } else {
            throw new BadRequestException(ErrorReasonCode.Invalid_Parameters);
        }
        if (jsonObject.has("addressLine2")) {
            companyDTO.setAddressLine2(jsonObject.getString("addressLine2"));
        } else {
            throw new BadRequestException(ErrorReasonCode.Invalid_Parameters);
        }
        if (jsonObject.has("addressLine3")) {
            companyDTO.setAddressLine3(jsonObject.getString("addressLine3"));
        }


        Company newCompany = companyService.createCompany(companyMapper.toEntity(companyDTO), companyLogo);

        return new ResponseEntity<>(newCompany, HttpStatus.OK);
    }

    @PostMapping(value = "/verify-email-templates")
    @PreAuthorize("hasAuthority('ROLE_INTERNAL_ADMIN')")
    public ResponseEntity<CompanyUserDTO> adminCreateVerifyEmailTemplate() {
        companyService.createCompanyEmailTemplate();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/companies/active/{companyUserEmail}")
    @PreAuthorize("hasAuthority('ROLE_INTERNAL_ADMIN')")
    public ResponseEntity<CompanyUserDTO> adminActiveCompanyUser(@PathVariable("companyUserEmail") String companyUserEmail) {

        return new ResponseEntity<>(companyUserMapper
                .toDTO(userService.adminActiveCompanyUser(companyUserEmail)),
                HttpStatus.OK);

    }
}
