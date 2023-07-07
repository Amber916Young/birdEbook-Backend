package com.orderit.app.dto;

import lombok.Data;

import java.util.List;


@Data
public class ServicePartnerDTO {
    private Long id;
    private String servicePartnerName;
    private int yearsOfExperience;
    private String description;
    private String servicePartnerLogoUrl;
    private String servicePartnerEmail;
    private String contactNumber;
    private String contactName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String category;
    private String contactEmail;
    private String companyWebsite;
    private List<ServicePartnerServiceDTO> servicePartnerServices;
}
