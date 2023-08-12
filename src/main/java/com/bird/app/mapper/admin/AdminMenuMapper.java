package com.bird.app.mapper.admin;

import com.bird.app.dto.admin.AdminMenuDTO;
import com.bird.common.entity.AdminMenu;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface AdminMenuMapper {
    AdminMenuDTO toDTO(AdminMenu adminMenu);

    AdminMenu toEntity(AdminMenuDTO adminMenuDTO);

    List<AdminMenuDTO> toDTOList(List<AdminMenu> adminMenus);
}
