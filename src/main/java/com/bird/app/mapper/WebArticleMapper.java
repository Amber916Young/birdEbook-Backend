package com.bird.app.mapper;

import com.bird.app.dto.ArticleDTO;
import com.bird.app.dto.web.WebArticleDTO;
import com.bird.common.entity.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface WebArticleMapper {
    WebArticleDTO toDTO(Article article);

    Article toEntity(WebArticleDTO articleDTO);

    List<WebArticleDTO> toDTOList(List<Article> articles);
}
