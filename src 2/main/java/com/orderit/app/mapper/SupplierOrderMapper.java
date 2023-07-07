package com.orderit.app.mapper;

import com.orderit.app.dto.SupplierOrderDTO;
import com.orderit.common.entity.SupplierOrder;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {SupplierOrderItemMapper.class})
public interface SupplierOrderMapper {
    SupplierOrderDTO toDTO(SupplierOrder supplierOrder);

    SupplierOrder toEntity(SupplierOrderDTO supplierOrderDTO);

    List<SupplierOrderDTO> toDTOList(Set<SupplierOrder> supplierOrders);
}
