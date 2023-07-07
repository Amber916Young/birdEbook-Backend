package com.orderit.app.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PartnerDTO {
    private Long id;

    private String partnerName;

    private String description;

    private String partnerLogoUrl;

    private String partnerEmail;

    private String contactNumber;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private Set<PartnerProductDTO> partnerProducts;
}
