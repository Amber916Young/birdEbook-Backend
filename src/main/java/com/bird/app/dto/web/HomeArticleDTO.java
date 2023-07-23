package com.bird.app.dto.web;

import com.bird.app.dto.CategoryTreeDTO;
import com.bird.app.dto.TagsDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class HomeListArticlesDTO {
    private List<HomeArticlesDTO> articles;
    private CategoryTreeDTO category;
}
@Data
class  HomeArticlesDTO{
    private WebArticleDTO article;
    private List<TagsDTO> tagsList = new ArrayList<>();
}