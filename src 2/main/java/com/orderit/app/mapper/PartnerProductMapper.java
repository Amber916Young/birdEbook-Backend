package com.orderit.app.mapper;

import com.orderit.app.dto.PartnerProductDTO;
import com.orderit.common.entity.PartnerProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface PartnerProductMapper {
    PartnerProductDTO toDTO(PartnerProduct partnerProduct);

    List<PartnerProductDTO> toDTOList(List<PartnerProduct> partnerProducts);
}
