package com.orderit.app.mapper;

import com.orderit.app.dto.ProductDTO;
import com.orderit.common.entity.SupplierProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface SupplierProductMapper {
    ProductDTO toDTO(SupplierProduct product);

    List<ProductDTO> toDTOList(List<SupplierProduct> products);
}
