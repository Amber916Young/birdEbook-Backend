package com.bird.app.mapper;

import com.bird.app.dto.ArticleActionDTO;
import com.bird.common.entity.ArticleAction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface ArticleActionMapper {
    ArticleActionDTO toDTO(ArticleAction articleAction);

    ArticleAction toEntity(ArticleActionDTO articleActionDTO);

    List<ArticleActionDTO> toDTOList(List<ArticleAction> articleActions);
}
