package com.bird.app.mapper;

import com.bird.app.dto.ArticleDraftDTO;
import com.bird.common.entity.ArticleDraft;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName:ArticleDraftMapper
 * @Auther: yyj
 * @Description:
 * @Date: 23/07/2023 21:22
 * @Version: v1.0
 */
@Mapper(componentModel = "spring", uses = {})
public interface ArticleDraftMapper {
    ArticleDraftDTO toDTO( ArticleDraft articleDraft);

    ArticleDraft toEntity(ArticleDraftDTO articleDraftDTO);

    List<ArticleDraftDTO> toDTOList(List< ArticleDraft> articleDrafts);
}
