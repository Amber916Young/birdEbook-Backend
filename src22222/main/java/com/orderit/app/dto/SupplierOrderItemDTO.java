package com.orderit.app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplierOrderItemDTO {
    private Long id;

    private Long supplierProductId;

    private String supplierProductName;

    private String nickname;

    private String unit;

    private BigDecimal quantity;

    private BigDecimal vat;

    private BigDecimal price;
}
