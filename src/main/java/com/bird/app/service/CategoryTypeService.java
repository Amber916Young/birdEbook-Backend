package com.bird.app.service;

import com.bird.app.dto.CategoryTreeDTO;
import com.bird.app.mapper.CategoryTypeMapper;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.CategoryType;
import com.bird.common.entity.Tags;
import com.bird.common.repository.CategoryTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:CategoryTypeService
 * @Auther: yyj
 * @Description:
 * @Date: 12/07/2023 22:40
 * @Version: v1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryTypeService {
    final CategoryTypeRepository categoryTypeRepository;
    final CategoryTypeMapper categoryTypeMapper;

    public List<CategoryTreeDTO> findAllCategoryAndChildren() {
        List<CategoryType> categoryTypes = categoryTypeRepository.findAll();

        Map<Long, CategoryTreeDTO> categoryMap = new HashMap<>();
        categoryTypes.forEach(category -> {
            categoryMap.put(category.getId(), categoryTypeMapper.toDTO(category));
        });
        List<CategoryTreeDTO> categoryTree = new ArrayList<>();

        categoryTypes.forEach(category -> {
            CategoryTreeDTO categoryDTO = categoryMap.get(category.getId());
            if (category.getPid() == 0) {
                categoryTree.add(categoryDTO);
            } else {
                CategoryTreeDTO parentCategory = categoryMap.get(category.getPid());
                if (parentCategory != null) {
                    parentCategory.getChildren().add(categoryDTO);
                }
            }
        });

        return categoryTree;


    }

    public CategoryType createCategory(CategoryType categoryType) {
        return categoryTypeRepository.save(categoryType);
    }

    public CategoryType updateCategory(CategoryType categoryType) {
        CategoryType pre = getCategoryTypeById(categoryType.getId());
        categoryType.setCreateTime(pre.getCreateTime());
        return categoryTypeRepository.save(categoryType);
    }

    private CategoryType getCategoryTypeById(Long id) {
        return categoryTypeRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Category_Cannot_Found));
    }

}
