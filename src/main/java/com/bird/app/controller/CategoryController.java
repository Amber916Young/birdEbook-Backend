package com.bird.app.controller;

import com.bird.app.dto.CategoryTreeDTO;
import com.bird.app.mapper.CategoryMapper;
import com.bird.app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author birdyyoung
 * @ClassName:CategoryController
 * @Description:
 * @Date: 12/07/2023 22:25
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllCategoryTree() {
        return new ResponseEntity<>(categoryService.findAllCategoryAndChildren(), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryTreeDTO categoryTreeDTO) {
        return new ResponseEntity<>(categoryMapper.toDTO(categoryService.createCategory(categoryMapper.toEntity(categoryTreeDTO))), HttpStatus.OK);
    }
    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryTreeDTO categoryTreeDTO) {
        return new ResponseEntity<>(categoryMapper.toDTO(categoryService.updateCategory(categoryMapper.toEntity(categoryTreeDTO))), HttpStatus.OK);
    }

    /**
     * TODO
     * 删除category
     * 需要注意  category 属于树形结构 跟节点删除后，孩子节点作为根结点
     *    Tree 数据结构 深度优先算法
     * */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTags(@PathVariable("id") Long id) {
        return new ResponseEntity<>(categoryService.generateCategoryTreeAndDelete(id),HttpStatus.OK);
    }

}
