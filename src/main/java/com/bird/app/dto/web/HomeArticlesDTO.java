package com.bird.app.dto.web;

import com.bird.app.dto.TagsDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:HomeArticlesDTO
 * @Auther: yyj
 * @Description:
 * @Date: 31/07/2023 21:13
 * @Version: v1.0
 */
@Data
public class  HomeArticlesDTO{
    private WebArticleDTO article;
    private List<TagsDTO> tagsList = new ArrayList<>();
}