package com.orderit.app.mapper;

import com.orderit.app.dto.FavouriteProductDTO;
import com.orderit.app.dto.FavouriteSupplierDTO;
import com.orderit.common.entity.FavouriteProduct;
import com.orderit.common.entity.FavouriteSupplier;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {})
public interface FavouriteSupplierMapper {
    FavouriteSupplierDTO toDTO(FavouriteSupplier favouriteProduct);

    FavouriteSupplier toEntity(FavouriteSupplierDTO favourite);

    List<FavouriteSupplierDTO> toDTOList(Set<FavouriteSupplier> favouriteProducts);
}
