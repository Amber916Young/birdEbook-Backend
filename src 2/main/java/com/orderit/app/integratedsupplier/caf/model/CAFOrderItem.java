package com.orderit.app.integratedsupplier.caf.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CAFOrderItem {
    private BigDecimal quantity;

    private Long productId;
}
