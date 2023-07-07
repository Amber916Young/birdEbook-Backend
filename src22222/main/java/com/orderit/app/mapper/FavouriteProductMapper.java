package com.orderit.app.mapper;

import com.orderit.app.dto.FavouriteProductDTO;
import com.orderit.common.entity.FavouriteProduct;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {})
public interface FavouriteProductMapper {
    FavouriteProductDTO toDTO(FavouriteProduct favouriteProduct);

    FavouriteProduct toEntity(FavouriteProductDTO favourite);

    List<FavouriteProductDTO> toDTOList(Set<FavouriteProduct> favouriteProducts);
}
