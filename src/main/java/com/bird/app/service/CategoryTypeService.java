package com.bird.app.service;

import com.bird.app.dto.CategoryTreeDTO;
import com.bird.app.mapper.CategoryTypeMapper;
import com.bird.common.entity.CategoryType;
import com.bird.common.repository.CategoryTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
                // Category is a root node
                categoryTree.add(categoryDTO);
            } else {
                // Category has a parent, add it as a child to the parent category
                CategoryTreeDTO parentCategory = categoryMap.get(category.getPid());
                if (parentCategory != null) {
                    parentCategory.getChildren().add(categoryDTO);
                }
            }
        });

        return categoryTree;


    }
}
