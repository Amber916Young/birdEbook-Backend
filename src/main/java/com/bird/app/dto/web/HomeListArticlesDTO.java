package com.bird.app.dto.web;

import com.bird.app.dto.CategoryTreeDTO;
import lombok.Data;

import java.util.List;

@Data
public class HomeListArticlesDTO {
    private List<HomeArticlesDTO> articles;
    private CategoryTreeDTO category;
}
