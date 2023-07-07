package com.orderit.app.dto;

import com.orderit.common.enums.ProductType;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private Long companyId;

    private Long supplierId;

    private String productName;
    private String productCode;

    private String category;

    private String description;

    private String productImageUrl;

    private BigDecimal vat;

    private BigDecimal price;

    private String unit;

    private String nickname;

    private Long favId;
    private ProductType type;
}
