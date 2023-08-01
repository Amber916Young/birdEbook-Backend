package com.bird.app.mapper;

import com.bird.app.dto.AdminArticleDTO;
import com.bird.app.dto.ArticleDTO;
import com.bird.app.dto.web.WebArticleDTO;
import com.bird.common.entity.Article;
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
public interface ArticleMapper {
    ArticleDTO toDTO(Article article);

    Article toEntity(ArticleDTO articleDTO);

    List<ArticleDTO> toDTOList(List<Article> articles);

    WebArticleDTO toWebDTO(Article article);

    Article toWebEntity(WebArticleDTO webArticleDTO);

    AdminArticleDTO toAdminDTO(Article article);
}
