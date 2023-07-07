package com.orderit.app.integratedsupplier.buytillrolls.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BuyTillRollsOrder {
    private String customerAccountNumber;

    private String customerName;

    private LocalDate deliveryDate;

    private Long externalId;

    private List<BuyTillRollsOrderItem> items;
}
