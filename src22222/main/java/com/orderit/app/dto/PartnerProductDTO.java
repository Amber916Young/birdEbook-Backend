package com.orderit.app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PartnerProductDTO {
    private Long id;

    private Long partnerId;

    private String productName;

    private String description;

    private String productImageUrl;

    private BigDecimal vat;

    private BigDecimal price;
}
