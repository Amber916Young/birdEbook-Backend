package com.orderit.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryServicePartnerDTO {
    String category;
    List<ServicePartnerDTO> servicePartners;
}
