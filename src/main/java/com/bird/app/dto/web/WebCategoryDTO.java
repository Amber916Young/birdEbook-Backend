package com.bird.app.dto.web;

import lombok.Data;

@Data
public class WebCategoryDTO {
    private Long id;
    private String name;
    private Long pid;
    private String icon;
    private String description;
}
