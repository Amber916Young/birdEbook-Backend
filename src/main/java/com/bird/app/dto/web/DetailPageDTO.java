package com.bird.app.dto.web;

import com.bird.app.dto.CategoryTreeDTO;
import com.bird.common.entity.ArticleAction;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DetailPageDTO {
    private WebArticleDTO article;
    private Set<WebTagsDTO> tagsDTOSet = new HashSet<>();
    private CategoryTreeDTO category;
    private Set<ArticleAction> actionList = new HashSet<>();
}
