package com.bird.app.mapper;

import com.bird.app.dto.WikiActionDTO;
import com.bird.common.entity.WikiAction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface WikiActionMapper {
    WikiActionDTO toDTO(WikiAction wikiAction);

    WikiAction toEntity(WikiActionDTO wikiActionDTO);

    List<WikiActionDTO> toDTOList(List<WikiAction> wikiActions);
}
