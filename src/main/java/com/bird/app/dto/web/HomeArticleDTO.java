package com.bird.app.dto.web;

import com.bird.app.dto.ArticleDTO;
import com.bird.app.dto.TagsDTO;
import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class HomeArticleDTO {
    private ArticleDTO article;
    private List<TagsDTO> tagsList = new ArrayList<>();

}
