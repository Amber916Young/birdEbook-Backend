package com.orderit.app.integratedsupplier.pureoil.mapper;

import com.orderit.app.dto.ProductDTO;
import com.orderit.app.integratedsupplier.pureoil.model.PureOilProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface PureOilProductMapper {
    ProductDTO toProductDTO(PureOilProduct product);

    List<ProductDTO> toProductDTOList(List<PureOilProduct> products);
}
