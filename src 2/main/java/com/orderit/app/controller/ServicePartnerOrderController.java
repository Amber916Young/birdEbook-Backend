package com.orderit.app.controller;

import com.orderit.app.dto.ServicePartnerOrderDTO;
import com.orderit.app.service.ServicePartnerOrderService;
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
@RequestMapping("/api/servicePartnerOrder")
@RequiredArgsConstructor
@Slf4j
public class ServicePartnerOrderController {
    private final ServicePartnerOrderService servicePartnerOrderService;


    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createServicePartnerOrder(@Valid @RequestBody ServicePartnerOrderDTO servicePartnerOrderDTO) {
        try {
            servicePartnerOrderService.createServicePartnerOrder(servicePartnerOrderDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadRequestException(ErrorReasonCode.Invalid_Parameters);
        }
    }


}
