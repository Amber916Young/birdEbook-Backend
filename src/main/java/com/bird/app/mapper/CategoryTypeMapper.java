package com.bird.app.mapper;

import com.bird.app.dto.CategoryTreeDTO;
import com.bird.app.dto.TagsDTO;
import com.bird.common.entity.CategoryType;
import com.bird.common.entity.Tags;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName:CategoryTypeMapper
 * @Auther: yyj
 * @Description:
 * @Date: 12/07/2023 22:40
 * @Version: v1.0
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoryTypeMapper {
    CategoryTreeDTO toDTO(CategoryType categoryType);

    CategoryType toEntity(CategoryTreeDTO categoryTreeDTO);

    List<CategoryTreeDTO> toDTOList(List<CategoryType> categoryTypes);


}
