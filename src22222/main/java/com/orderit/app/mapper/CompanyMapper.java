package com.orderit.app.mapper;

import com.orderit.app.dto.CompanyDTO;
import com.orderit.common.entity.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface CompanyMapper {
    CompanyDTO toDTO(Company company);

    Company toEntity(CompanyDTO companyDTO);

    List<CompanyDTO> toDTOList(List<Company> companies);
}
