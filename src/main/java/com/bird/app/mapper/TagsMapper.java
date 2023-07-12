package com.bird.app.mapper;

import com.bird.app.dto.TagsDTO;
import com.bird.common.entity.Tags;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author birdyyoung
 */
@Mapper(componentModel = "spring", uses = {})
public interface TagsMapper {
    TagsDTO toDTO(Tags tags);

    Tags toEntity(TagsDTO tagsDTO);

    List<TagsDTO> toDTOList(List<Tags> tagsList);
}
