package com.orderit.app.integratedsupplier.buytillrolls.mapper;

import com.orderit.app.dto.ProductDTO;
import com.orderit.app.integratedsupplier.buytillrolls.model.BuyTillRollsProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface BuyTillRollsProductMapper {
    ProductDTO toProductDTO(BuyTillRollsProduct product);

    List<ProductDTO> toProductDTOList(List<BuyTillRollsProduct> products);
}
