package com.orderit.app.mapper;

import com.orderit.app.dto.CompanyDTO;
import com.orderit.app.dto.CompanyProfileDTO;
import com.orderit.app.dto.CompanyUserDTO;
import com.orderit.common.entity.Company;
import com.orderit.common.entity.CompanyUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface CompanyUserMapper {
    CompanyUserDTO toDTO(CompanyUser company);

    CompanyUser toEntity(CompanyUserDTO companyDTO);

    List<CompanyUserDTO> toDTOList(List<CompanyUser> companies);
}
