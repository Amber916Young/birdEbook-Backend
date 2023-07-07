package com.orderit.app.integratedsupplier.pureoil.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PureOilOrder {
    private String customerAccountNumber;

    private String customerName;

    private LocalDate deliveryDate;

    private Long externalId;

    private List<PureOilOrderItem> items;
}
