package com.bird.app.dto.admin;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

/**
 * @ClassName:AdminMenuDTO
 * @Auther: yyj
 * @Description:
 * @Date: 12/08/2023 12:42
 * @Version: v1.0
 */
@Data
public class AdminMenuDTO {
    private Long id;
    private Long pid;
    private String name;
    private String link;
    private String icon;
    private List<AdminMenuDTO> children;
}
