package com.bird.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DetailArticleDTO {
    private ArticleDTO article;
    private List<TagsDTO> tagsList = new ArrayList<>();
    @JsonIgnore
    private CategoryTreeDTO category;
}