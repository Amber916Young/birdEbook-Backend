package com.orderit.app.integratedsupplier.pureoil.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PureOilProduct {
    private Long id;

    private String productName;

    private String description;

    private BigDecimal vat;

    private BigDecimal price;
}
