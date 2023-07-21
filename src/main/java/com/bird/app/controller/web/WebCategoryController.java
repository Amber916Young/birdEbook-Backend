package com.bird.app.controller.web;

import com.bird.app.dto.CategoryTreeDTO;
import com.bird.app.mapper.ArticleMapper;
import com.bird.app.mapper.CategoryMapper;
import com.bird.app.service.ArticleService;
import com.bird.app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author birdyyoung
 * @ClassName:WebCategoryController
 * @Description:
 * @Date: 10/07/2023 14:46
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/web/categories")
@RequiredArgsConstructor
@Slf4j
public class WebCategoryController {

    private final CategoryMapper categoryMapper;
    private final ArticleMapper articleMapper;
    private final CategoryService categoryService;
    private final ArticleService articleService;

    // filter by category
    @GetMapping(value = "/{cateId}",produces = "application/json")
    public ResponseEntity<?> getArticlesListByCategory(@PathVariable("cateId") Long  cateId){
        return new ResponseEntity<>( HttpStatus.OK);

    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>( categoryService.findAllCategoryAndChildren(), HttpStatus.OK);
    }

}
