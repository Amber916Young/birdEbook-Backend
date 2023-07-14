package com.bird.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:CategoryTree
 * @Auther: yyj
 * @Description:
 * @Date: 12/07/2023 22:26
 * @Version: v1.0
 */
@Data
public class CategoryTreeDTO {
    private Long id;
    private String name;
    private Long pid;
    private String icon;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryTreeDTO> children = new ArrayList<>();
}


