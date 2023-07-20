package com.bird.app.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DetailArticleDTO {
    private ArticleDTO article;
    private List<TagsDTO> tagsList = new ArrayList<>();
    private CategoryTreeDTO category;
}