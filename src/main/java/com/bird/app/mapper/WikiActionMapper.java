package com.bird.app.mapper;

import com.bird.app.dto.WikiActionDTO;
import com.bird.common.entity.ArticleAction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface WikiActionMapper {
    WikiActionDTO toDTO(ArticleAction articleAction);

    ArticleAction toEntity(WikiActionDTO wikiActionDTO);

    List<WikiActionDTO> toDTOList(List<ArticleAction> articleActions);
}
