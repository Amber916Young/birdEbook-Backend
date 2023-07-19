package com.bird.app.mapper;

import com.bird.app.dto.CategoryTreeDTO;
import com.bird.common.entity.Category;
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
public interface CategoryMapper {
    CategoryTreeDTO toDTO(Category category);

    Category toEntity(CategoryTreeDTO categoryTreeDTO);

    List<CategoryTreeDTO> toDTOList(List<Category> categories);


}
