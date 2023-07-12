package com.bird.app.controller;

import com.bird.app.dto.CategoryTreeDTO;
import com.bird.app.mapper.CategoryTypeMapper;
import com.bird.app.service.CategoryTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:CategoryController
 * @Auther: yyj
 * @Description:
 * @Date: 12/07/2023 22:25
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryTypeController {

    private final CategoryTypeMapper categoryTypeMapper;
    private final CategoryTypeService categoryTypeService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllCategoryTree() {
        List<CategoryTreeDTO>  categoryTreeDTOS = categoryTypeService.findAllCategoryAndChildren();



        return new ResponseEntity<>(categoryTreeDTOS, HttpStatus.OK);
    }


}
