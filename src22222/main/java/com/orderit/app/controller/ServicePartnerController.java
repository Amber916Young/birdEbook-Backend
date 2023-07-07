package com.orderit.app.controller;

import com.orderit.app.dto.CategoryServicePartnerDTO;
import com.orderit.app.dto.ServicePartnerDTO;
import com.orderit.app.service.ServicePartnerService;
import com.orderit.common.exception.BadRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/servicePartner")
@RequiredArgsConstructor
@Slf4j
public class ServicePartnerController {
    private final ServicePartnerService servicePartnerService;


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllPartnerServicesAndServices() {
        try {
            List<CategoryServicePartnerDTO> servicePartners = servicePartnerService.getAllPartnerServicesAndServices();
            return new ResponseEntity<>(servicePartners, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadRequestException(ErrorReasonCode.Invalid_Parameters);
        }
    }


}
