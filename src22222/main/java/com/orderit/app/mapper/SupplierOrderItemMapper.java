package com.orderit.app.mapper;

import com.orderit.app.dto.SupplierOrderItemDTO;
import com.orderit.common.entity.SupplierOrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {})
public interface SupplierOrderItemMapper {
    @Named("mapNullQuantityToZero")
    public static BigDecimal mapNullQuantityToZero(BigDecimal quantity) {
        return quantity == null ? BigDecimal.ZERO : quantity;
    }

    @Named("mapZeroQuantityToNull")
    public static BigDecimal mapZeroQuantityToNull(BigDecimal quantity) {
        return quantity.compareTo(BigDecimal.ZERO) == 0 ? null : quantity;
    }

    @Mapping(source = "quantity", target = "quantity", qualifiedByName = "mapZeroQuantityToNull")
    SupplierOrderItemDTO toDTO(SupplierOrderItem supplierOrderItem);

    @Mapping(source = "quantity", target = "quantity", qualifiedByName = "mapNullQuantityToZero")
    SupplierOrderItem toEntity(SupplierOrderItemDTO orderItemDTO);

    List<SupplierOrderItemDTO> toDTOList(Set<SupplierOrderItem> supplierOrderItems);

    Set<SupplierOrderItem> toEntitySet(List<SupplierOrderItemDTO> orderItemDTOS);
}
