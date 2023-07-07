package com.orderit.app.integratedsupplier.caf.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CAFOrder {
    private String customerAccountNumber;

    private String customerName;

    private LocalDate deliveryDate;

    private Long externalId;

    private List<CAFOrderItem> items;
}
