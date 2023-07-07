package com.orderit.app.dto.email;

import com.orderit.common.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderEmailDTO {
    private String supplierName;
    private Long orderId;
    private String companyName;
    private String companyContactName;
    private String companyContactNumber;
    private String companyAccountNumber;
    private List<OrderProductEmailDTO> products;
    private String contactEmail;
    private String companyAddressLine1;
    private String companyAddressLine2;
    private String companyAddressLine3;
    private String confirmLink;
    private String rejectLink;
    private String deliveryDate;
    private String orderDate;

}
