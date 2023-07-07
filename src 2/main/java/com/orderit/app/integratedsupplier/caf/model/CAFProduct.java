package com.orderit.app.integratedsupplier.caf.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CAFProduct {
    private Long id;

    private String productName;

    private String description;

    private BigDecimal vat;

    private BigDecimal price;
}
