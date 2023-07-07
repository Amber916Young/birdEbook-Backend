package com.orderit.app.controller;

import com.orderit.app.dto.SupplierDTO;
import com.orderit.app.dto.ProductDTO;
import com.orderit.app.mapper.SupplierMapper;
import com.orderit.app.service.SupplierService;
import com.orderit.common.entity.Supplier;
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
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@Slf4j
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliersDTO();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping(value = "/{supplierId}/products", produces = "application/json")
    public ResponseEntity<List<ProductDTO>> getSupplierProductsBySupplierId(@PathVariable("supplierId") long supplierId) {
        List<ProductDTO> supplierProducts = supplierService.getAllSupplierProductsDTO(supplierId);
        return new ResponseEntity<>(supplierProducts, HttpStatus.OK);
    }

    @GetMapping(value = "/{supplierId}",produces = "application/json")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable("supplierId") long supplierId) {
        Supplier supplier = supplierService.getSupplierById(supplierId);
        return new ResponseEntity<>(
                supplierMapper.toDTO(supplier),
                HttpStatus.OK);
    }
}
