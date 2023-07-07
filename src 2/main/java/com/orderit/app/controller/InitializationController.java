package com.orderit.app.controller;

import com.orderit.app.dto.SupplierDTO;
import com.orderit.app.dto.SupplierOrderDTO;
import com.orderit.app.dto.ProductDTO;
import com.orderit.app.mapper.SupplierOrderMapper;
import com.orderit.app.service.SupplierOrderService;
import com.orderit.app.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/init")
@RequiredArgsConstructor
@Slf4j
public class InitializationController {
    private final SupplierService supplierService;
    private final SupplierOrderService supplierOrderService;
    private final SupplierOrderMapper supplierOrderMapper;

    @GetMapping(value = "/suppliers",produces = "application/json")
    public ResponseEntity<List<SupplierDTO>> initializationSuppliers() {
        List<SupplierDTO> suppliers =  supplierService.getAllSuppliersDTO();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping(value = "/suppliers/{supplierId}/products",produces = "application/json")
    public ResponseEntity<List<ProductDTO>> initializationSuppliersProducts(@PathVariable("supplierId") long supplierId) {
        List<ProductDTO> products =  supplierService.getAllSupplierProductsDTO(supplierId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/orders",produces = "application/json")
    public ResponseEntity<List<SupplierOrderDTO>> initializationOrders() {
        List<SupplierOrderDTO> supplierOrder =  supplierOrderMapper.toDTOList(supplierOrderService.getOrdersForCurrentUser());
        return new ResponseEntity<>(supplierOrder, HttpStatus.OK);
    }
}
