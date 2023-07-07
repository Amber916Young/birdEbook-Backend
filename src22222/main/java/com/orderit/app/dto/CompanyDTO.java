package com.orderit.app.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CompanyDTO {
    private Long id;
    private String companyName;
    private String companyLogoUrl;
    private String companyEmail;
    private String contactName;
    private String contactNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private Set<CompanyUserDTO> companyUsers;
}
