package com.orderit.app.controller;

import com.orderit.app.dto.CompanyUserDTO;
import com.orderit.app.dto.UserProfileDTO;
import com.orderit.app.mapper.CompanyUserMapper;
import com.orderit.app.service.UserService;
import com.orderit.common.entity.CompanyUser;
import com.orderit.common.exception.BadRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final UserService userService;
    private final CompanyUserMapper companyUserMapper;

    @PutMapping(produces = "application/json")
    public ResponseEntity<UserProfileDTO> updateProfile(@Valid @RequestBody UserProfileDTO userProfileDTO) {
        try {
            CompanyUser companyUser = userService.updateCompanyUser(userProfileDTO);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadRequestException(ErrorReasonCode.Invalid_Parameters);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CompanyUserDTO> getProfile() {
        try {
            CompanyUser companyUser = userService.getUserById();
            return new ResponseEntity<>(companyUserMapper.toDTO(companyUser), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadRequestException(ErrorReasonCode.Invalid_Parameters);
        }
    }

}
