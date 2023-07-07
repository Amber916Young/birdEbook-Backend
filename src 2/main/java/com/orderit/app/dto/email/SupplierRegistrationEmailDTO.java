package com.orderit.app.dto.email;

import lombok.Data;

@Data
public class SupplierRegistrationEmailDTO {
    private Long supplierId;
    private String supplierName;
    private String supplierEmail;
    private String companyName;
    private String companyEmail;
    private String companyAccountNumber;
    private String address;
    private String ownerName;
    private String ownerAccountNumber;
    private String ownerEmail;

    private String ownersName;
    private String ownersAccountNumber;
    private String ownersEmail;
}
