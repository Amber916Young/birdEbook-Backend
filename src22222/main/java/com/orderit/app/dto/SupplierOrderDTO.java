package com.orderit.app.dto;

import com.orderit.common.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class SupplierOrderDTO {
    private Long id;

    private Long supplierId;

    private String supplierName;

    private String supplierLogoUrl;

    private OrderStatus status;

    private ZonedDateTime createTime;

    private LocalDate deliveryDate;

    private String createdBy;
    private BigDecimal totalVat;

    private BigDecimal totalPrice;

    private List<SupplierOrderItemDTO> supplierOrderItems;
}
