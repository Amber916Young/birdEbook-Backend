package com.orderit.app.integratedsupplier.caf.mapper;

import com.orderit.app.dto.ProductDTO;
import com.orderit.app.integratedsupplier.caf.model.CAFProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface CAFProductMapper {
    ProductDTO toProductDTO(CAFProduct product);

    List<ProductDTO> toProductDTOList(List<CAFProduct> products);
}
