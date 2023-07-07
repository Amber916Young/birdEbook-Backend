package com.orderit.app.controller;

import com.orderit.app.dto.ProductDTO;
import com.orderit.app.dto.SupplierOrderDTO;
import com.orderit.app.mapper.CustomProductMapper;
import com.orderit.app.service.CustomProductService;
import com.orderit.common.entity.CustomProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customProducts")
@RequiredArgsConstructor
@Slf4j
public class CustomProductController {
    private final CustomProductService customProductService;
    private final CustomProductMapper customProductMapper;


    @PostMapping(produces="application/json")
    public ResponseEntity<ProductDTO> createCustomProduct(@Valid @RequestBody ProductDTO productDTO) {
        CustomProduct savedCustomProduct = customProductService.createCustomProduct(customProductMapper.toEntity(productDTO));

        return new ResponseEntity<>(customProductMapper.toDTO(savedCustomProduct), HttpStatus.CREATED);
    }

    @PutMapping(produces="application/json")
    public ResponseEntity<ProductDTO> updateCustomProduct(@Valid @RequestBody ProductDTO productDTO) {
        CustomProduct updateCustomProduct = customProductService.updateCustomProduct(customProductMapper.toEntity(productDTO));
        return new ResponseEntity<>(customProductMapper.toDTO(updateCustomProduct), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierOrderDTO> deleteCustomProduct(@PathVariable Long id) {
        customProductService.deleteCustomProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
