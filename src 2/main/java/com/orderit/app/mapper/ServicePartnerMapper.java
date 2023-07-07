package com.orderit.app.mapper;

import com.orderit.app.dto.ServicePartnerDTO;
import com.orderit.common.entity.ServicePartner;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface ServicePartnerMapper {
    ServicePartnerDTO toDTO(ServicePartner servicePartner);
    ServicePartner toEntity(ServicePartnerDTO servicePartnerDTO);
    List<ServicePartnerDTO> toDTOList(List<ServicePartner> servicePartners);
}
