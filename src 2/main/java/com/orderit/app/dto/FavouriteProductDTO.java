package com.orderit.app.dto;

import lombok.Data;

@Data
public class FavouriteProductDTO {
    private Long id;

    private Long supplierId;

    private Long supplierProductId;

    private String nickname;
}
