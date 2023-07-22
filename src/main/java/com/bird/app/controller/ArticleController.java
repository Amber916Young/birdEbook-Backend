package com.bird.app.controller;

import com.bird.app.dto.DetailArticleDTO;
import com.bird.app.dto.ArticleDTO;
import com.bird.app.mapper.ArticleMapper;
import com.bird.app.service.ArticleService;
import com.bird.common.entity.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author birdyyoung
 * @ClassName:WikiController
 * @Description:
 * @Date: 01/07/2023 19:48
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleMapper articleMapper;


    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.createArticle(articleMapper.toEntity(articleDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllArticlesList(@RequestParam("pageNumber") int pageNumber,
                                                @RequestParam("pageSize") int pageSize,
                                                @RequestParam("queryStr") String queryStr) {


        Page<Article> articles = articleService.getAllArticlesList(pageNumber, pageSize, queryStr);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    // Read an article by ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleMapper.toDTO(articleService.getArticleById(id)));
    }

    // Update an existing article
    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.updateArticleById(articleMapper.toEntity(articleDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete an article by ID
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
