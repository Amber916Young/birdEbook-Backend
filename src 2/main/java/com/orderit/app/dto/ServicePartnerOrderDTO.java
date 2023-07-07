package com.orderit.app.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ServicePartnerOrderDTO {
    private Long servicePartnerId;
}
