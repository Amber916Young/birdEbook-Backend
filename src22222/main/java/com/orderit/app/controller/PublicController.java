package com.orderit.app.controller;

import com.amazonaws.services.drs.model.AccessDeniedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderit.app.dto.CompanyDTO;
import com.orderit.app.dto.CompanyUserDTO;
import com.orderit.app.dto.RegistrationDTO;
import com.orderit.app.dto.SupplierOrderDTO;
import com.orderit.app.mapper.CompanyMapper;
import com.orderit.app.mapper.CompanyUserMapper;
import com.orderit.app.mapper.SupplierOrderMapper;
import com.orderit.app.service.CompanyService;
import com.orderit.app.service.RegisterService;
import com.orderit.app.service.SupplierOrderService;
import com.orderit.app.service.UserService;
import com.orderit.common.entity.Company;
import com.orderit.common.entity.CompanyUser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
@Slf4j
public class PublicController {
    private final SupplierOrderService supplierOrderService;
    private final SupplierOrderMapper supplierOrderMapper;
    private final RegisterService registerService;
    @Value("${feign.caf.token}")
    private String cafToken;


    @SneakyThrows
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> registerCompanyAndOwner(@RequestBody RegistrationDTO registrationDTO) {
        registerService.registerCompanyAndCompanyUser(registrationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @SneakyThrows
    @PutMapping(value = "/supplierOrderConfirmation/emailApproval/{encodedSupplierOrderId}")
    public void confirmSupplierOrder(@PathVariable("encodedSupplierOrderId") String encodedSupplierOrderId) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedSupplierOrderId);
        Long id = Long.valueOf(new String(decodedBytes, StandardCharsets.UTF_8));
        supplierOrderService.confirmOrder(id);
    }

    @PutMapping(value = "/supplierOrderConfirmation/externalApproval")
    public void confirmSupplierOrderByExternalSupplier(@RequestParam Long id, @RequestParam String token) {
        if (token == null || !token.equals(cafToken)) {
            throw new AccessDeniedException("");
        }
        supplierOrderService.confirmOrder(id);
    }

    @PutMapping(value = "/supplierOrderConfirmation/emailRejection/{encodedSupplierOrderId}")
    public void emailRejectSupplierOrderByExternalSupplier(@PathVariable("encodedSupplierOrderId") String encodedSupplierOrderId) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedSupplierOrderId);
        Long id = Long.valueOf(new String(decodedBytes, StandardCharsets.UTF_8));
        supplierOrderService.rejectOrder(id);
    }

    @PutMapping(value = "/supplierOrderConfirmation/externalRejection")
    public void rejectSupplierOrderByExternalSupplier(@RequestParam Long id, @RequestParam String token) {
        if (token == null || !token.equals(cafToken)) {
            throw new AccessDeniedException("");
        }
        supplierOrderService.rejectOrder(id);
    }


    @PutMapping(value = "/supplierOrderConfirmation/externalApprovalWithUpdate")
    public void approvalSupplierOrderByExternalSupplier(@RequestParam String token, @Valid @RequestBody SupplierOrderDTO supplierOrderDTO) {
        if (token == null || !token.equals(cafToken)) {
            throw new AccessDeniedException("");
        }
        supplierOrderService.updateOrderFromExternalSupplier(supplierOrderMapper.toEntity(supplierOrderDTO));
    }
}
