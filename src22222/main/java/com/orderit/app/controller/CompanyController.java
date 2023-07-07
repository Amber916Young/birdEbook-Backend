package com.orderit.app.controller;

import com.orderit.app.dto.CompanyDTO;
import com.orderit.app.dto.CompanyProfileDTO;
import com.orderit.app.dto.CompanyUserDTO;
import com.orderit.app.dto.CompanyUserProfileDTO;
import com.orderit.app.mapper.CompanyMapper;
import com.orderit.app.mapper.CompanyUserMapper;
import com.orderit.app.service.CompanyService;
import com.orderit.app.service.UserService;
import com.orderit.common.entity.Company;
import com.orderit.common.entity.CompanyUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final CompanyService companyService;
    private final UserService userService;
    private final CompanyMapper companyMapper;
    private final CompanyUserMapper companyUserMapper;

    @GetMapping(produces="application/json")
    public ResponseEntity<CompanyDTO> getCompanyAndUsers() {
        Company company = companyService.getCompanyForCurrentUser();

        return new ResponseEntity<>(companyMapper.toDTO(company), HttpStatus.OK);
    }

    @PutMapping(value="/profile", produces="application/json")
    public ResponseEntity<CompanyDTO> updateCompanyProfile(@Valid @RequestBody CompanyProfileDTO companyProfileDTO) {
        Company company = companyService.updateCompanyProfile(companyProfileDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value="/users", produces="application/json")
    public ResponseEntity<CompanyUserDTO> addUserToCompany(@Valid @RequestBody CompanyUserDTO companyUserDTO) {
        return new ResponseEntity<>(companyUserMapper.toDTO(userService.createCompanyUser(companyUserMapper.toEntity(companyUserDTO))), HttpStatus.OK);
    }

    @PutMapping(value="/users", produces="application/json")
    public ResponseEntity<?> updateUserToCompany(@Valid @RequestBody CompanyUserProfileDTO companyUserProfileDTO) {
        CompanyUser companyUser = userService.updateCompanyUserProfile(companyUserProfileDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/users/{userId}")
    public ResponseEntity<?> deleteFavourite(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
