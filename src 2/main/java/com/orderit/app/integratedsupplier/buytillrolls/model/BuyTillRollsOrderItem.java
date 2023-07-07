package com.orderit.app.integratedsupplier.buytillrolls.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyTillRollsOrderItem {
    private BigDecimal quantity;

    private Long productId;
}
