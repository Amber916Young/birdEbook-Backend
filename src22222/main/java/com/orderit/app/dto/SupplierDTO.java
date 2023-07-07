package com.orderit.app.dto;

import lombok.Data;

import java.util.Set;

@Data
public class SupplierDTO {
    private Long id;

    private String supplierName;

    private String description;

    private String cutOff;

    private String supplierLogoUrl;

    private String supplierEmail;

    private String contactNumber;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String deliveryDay;

    private String minOrder;

    private String category;

    private Boolean isIntegratedSupplier;

    private String shopName;

    private String accountNumber;

    private String deliveryDays;

    private int cutOffHour;

    private int cutOffMinute;

    private int orderLeadDays;
    
    private Set<ProductDTO> supplierProducts;
}
