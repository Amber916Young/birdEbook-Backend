package com.orderit.app.mapper;

import com.orderit.app.dto.PartnerDTO;
import com.orderit.common.entity.Partner;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PartnerProductMapper.class})
public interface PartnerMapper {
    PartnerDTO toDTO(Partner partner);

    Partner toEntity(PartnerDTO partnerDTO);

    List<PartnerDTO> toDTOList(List<Partner> partners);
}
