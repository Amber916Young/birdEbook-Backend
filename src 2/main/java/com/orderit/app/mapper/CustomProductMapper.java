package com.orderit.app.mapper;

import com.orderit.app.dto.ProductDTO;
import com.orderit.common.entity.CustomProduct;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {})
public interface CustomProductMapper {
    ProductDTO toDTO(CustomProduct customProduct);

    CustomProduct toEntity(ProductDTO customProductDTO);

    List<ProductDTO> toDTOList(Set<CustomProduct> customProducts);
}
