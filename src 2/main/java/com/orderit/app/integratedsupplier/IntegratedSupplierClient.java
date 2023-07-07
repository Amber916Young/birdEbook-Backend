package com.orderit.app.integratedsupplier;

import com.orderit.app.dto.ProductDTO;
import com.orderit.common.entity.SupplierOrder;

import java.util.List;

public interface IntegratedSupplierClient {
    List<ProductDTO> getProductList(String customerAccountNumber);

    void submitOrder(String customerAccountNumber, SupplierOrder order);
}
