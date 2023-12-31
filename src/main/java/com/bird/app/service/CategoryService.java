package com.bird.app.service;

import com.bird.app.dto.CategoryTreeDTO;
import com.bird.app.mapper.CategoryMapper;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.Category;
import com.bird.common.repository.CategoryRepository;
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
public class CategoryService {
    final CategoryRepository categoryRepository;
    final CategoryMapper categoryMapper;

    public List<CategoryTreeDTO> findAllCategoryAndChildren() {
        List<Category> categories = categoryRepository.findAll();

        Map<Long, CategoryTreeDTO> categoryMap = new HashMap<>();
        categories.forEach(category -> {
            categoryMap.put(category.getId(), categoryMapper.toDTO(category));
        });
        List<CategoryTreeDTO> categoryTree = new ArrayList<>();

        categories.forEach(category -> {
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



    public List<CategoryTreeDTO> generateCategoryTreeAndDelete(Long categoryIdToDelete) {
        List<Category> categories = categoryRepository.findAll();
        Map<Long, CategoryTreeDTO> categoryMap = new HashMap<>();
        categories.forEach(category -> {
            categoryMap.put(category.getId(), categoryMapper.toDTO(category));
        });

        List<CategoryTreeDTO> categoryTree = new ArrayList<>();
        List<CategoryTreeDTO> newRoots = new ArrayList<>();

        categories.forEach(category -> {
            CategoryTreeDTO categoryDTO = categoryMap.get(category.getId());
            if (category.getPid() == 0) {
                categoryTree.add(categoryDTO);
            } else {
                CategoryTreeDTO parentCategory = categoryMap.get(category.getPid());
                if (parentCategory != null) {
                    parentCategory.getChildren().add(categoryDTO);
                } else {
                    newRoots.add(categoryDTO);
                }
            }
        });

        CategoryTreeDTO categoryToDelete = categoryMap.get(categoryIdToDelete);
        if (categoryToDelete != null) {
            deleteCategoryRecursive(categoryToDelete, categoryMap);
        }

        categoryTree.addAll(newRoots);

        updateDatabase(categoryMap);

        return categoryTree;

    }

    private void deleteCategoryRecursive(CategoryTreeDTO category, Map<Long, CategoryTreeDTO> categoryMap) {
        List<CategoryTreeDTO> children = new ArrayList<>(category.getChildren());
        for (CategoryTreeDTO child : children) {
            deleteCategoryRecursive(child, categoryMap);
        }

        CategoryTreeDTO parentCategory = categoryMap.get(category.getPid());
        if (parentCategory != null) {
            parentCategory.getChildren().remove(category);
        } else {
            // If the deleted node is the root, remove it from the root list
            categoryMap.remove(category.getId());
        }
    }


    private void updateDatabase(CategoryTreeDTO category) {
    }

    private void updateDatabase(Map<Long, CategoryTreeDTO> categoryMap) {
        for (CategoryTreeDTO category : categoryMap.values()) {
            updateDatabase(category);
        }
    }
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        Category pre = getCategoryTypeById(category.getId());
        category.setCreateTime(pre.getCreateTime());
        return categoryRepository.save(category);
    }

    public Category getCategoryTypeById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Category_Cannot_Found));
    }


}
