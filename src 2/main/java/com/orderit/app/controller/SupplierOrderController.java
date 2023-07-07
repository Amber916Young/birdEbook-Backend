package com.orderit.app.controller;

import com.orderit.app.dto.SupplierOrderDTO;
import com.orderit.app.mapper.SupplierOrderMapper;
import com.orderit.app.service.SupplierOrderService;
import com.orderit.common.entity.SupplierOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/supplierOrders")
@RequiredArgsConstructor
@Slf4j
public class SupplierOrderController {
    private final SupplierOrderService supplierOrderService;
    private final SupplierOrderMapper supplierOrderMapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<SupplierOrderDTO>> getSupplierOrders() {
        List<SupplierOrderDTO> supplierOrderDTOList = supplierOrderMapper.toDTOList(supplierOrderService.getOrdersForCurrentUser());

        return new ResponseEntity<>(supplierOrderDTOList, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<SupplierOrderDTO> createSupplierOrder(@Valid @RequestBody SupplierOrderDTO supplierOrderDTO) {

        SupplierOrder savedSupplierOrder = supplierOrderService.createOrder(supplierOrderMapper.toEntity(supplierOrderDTO));

        return new ResponseEntity<>(supplierOrderMapper.toDTO(savedSupplierOrder), HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<SupplierOrderDTO> updateSupplierOrder(@Valid @RequestBody SupplierOrderDTO supplierOrderDTO) {
        SupplierOrder updatedSupplierOrder = supplierOrderService.updateOrder(supplierOrderMapper.toEntity(supplierOrderDTO));

        return new ResponseEntity<>(supplierOrderMapper.toDTO(updatedSupplierOrder), HttpStatus.OK);
    }

    @DeleteMapping("/{supplierOrderId}")
    public ResponseEntity<SupplierOrderDTO> deleteSupplierOrder(@PathVariable Long supplierOrderId) {
        supplierOrderService.deleteOrder(supplierOrderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
