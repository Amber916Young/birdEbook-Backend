package com.bird.app.mapper;

import com.bird.app.dto.WikiArticleDTO;
import com.bird.common.entity.WikiArticle;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName:WikiArticleMapper
 * @Auther: yyj
 * @Description:
 * @Date: 01/07/2023 20:44
 * @Version: v1.0
 */
@Mapper(componentModel = "spring", uses = {})
public interface WikiArticleMapper {
    WikiArticleDTO toDTO(WikiArticle wikiArticle);

    WikiArticle toEntity(WikiArticleDTO wikiArticleDTO);

    List<WikiArticleDTO> toDTOList(List<WikiArticle> wikiArticles);
}
