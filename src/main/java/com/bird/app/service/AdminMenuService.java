package com.bird.app.service;

import com.bird.app.dto.admin.AdminMenuDTO;
import com.bird.app.mapper.admin.AdminMenuMapper;
import com.bird.common.entity.AdminMenu;
import com.bird.common.repository.admin.AdminMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:AdminMenuService
 * @Auther: yyj
 * @Description:
 * @Date: 12/08/2023 12:25
 * @Version: v1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminMenuService {
    final AdminMenuRepository adminMenuRepository;
    final AdminMenuMapper adminMenuMapper;


    public List<AdminMenuDTO> findAllMenu() {
        List<AdminMenu> adminMenus = adminMenuRepository.findAll();
        Map<Long, AdminMenuDTO> adminMenuDTOMap = new HashMap<>();
        adminMenus.forEach(adminMenu -> {
            adminMenuDTOMap.put(adminMenu.getId(), adminMenuMapper.toDTO(adminMenu));
        });
        List<AdminMenuDTO> adminMenuList = new ArrayList<>();
        adminMenus.forEach(item -> {
            AdminMenuDTO adminMenu = adminMenuDTOMap.get(item.getId());
            if(item.getPid() == 0) {
                adminMenuList.add(adminMenu);
            }else {
                AdminMenuDTO parentAdminMenu = adminMenuDTOMap.get(item.getPid());
                if(parentAdminMenu != null) {
                    parentAdminMenu.getChildren().add(adminMenu);
                }
            }
        });
        return adminMenuList;
    }
}
