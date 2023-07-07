package com.orderit.app.mapper;

import com.orderit.app.dto.SupplierDTO;
import com.orderit.common.entity.Supplier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SupplierProductMapper.class})
public interface SupplierMapper {
    SupplierDTO toDTO(Supplier supplier);

    Supplier toEntity(SupplierDTO supplierDTO);

    List<SupplierDTO> toDTOList(List<Supplier> suppliers);
}
