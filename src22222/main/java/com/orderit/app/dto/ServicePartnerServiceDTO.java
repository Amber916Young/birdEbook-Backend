package com.orderit.app.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ServicePartnerServiceDTO {
    private Long id;
    private Long servicePartnerId;
    private String serviceName;
    private BigDecimal servicePrice;
    private BigDecimal vat;
}
