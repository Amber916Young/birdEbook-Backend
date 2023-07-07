package com.orderit.app.integratedsupplier.buytillrolls.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyTillRollsProduct {
    private Long id;

    private String productName;

    private String description;

    private BigDecimal vat;

    private BigDecimal price;
}
