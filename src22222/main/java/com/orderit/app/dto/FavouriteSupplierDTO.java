package com.orderit.app.dto;

import lombok.Data;

@Data
public class FavouriteSupplierDTO {
    private Long id;

    private Long supplierId;

    private String shopName;

    private String accountNumber;

}
