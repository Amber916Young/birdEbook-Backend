package com.orderit.app.dto.email;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProductEmailDTO {
    private String name;
    private BigDecimal quantity;
}
