package com.orderit.app.integratedsupplier.pureoil.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PureOilOrderItem {
    private BigDecimal quantity;

    private Long productId;
}
