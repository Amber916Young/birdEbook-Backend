package com.bird.app.controller.web;

import com.bird.app.dto.*;
import com.bird.app.mapper.ArticleDraftMapper;
import com.bird.app.mapper.ArticleMapper;
import com.bird.app.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/web/articles")
@RequiredArgsConstructor
@Slf4j
public class WebArticleController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;
    private final ArticleDraftMapper articleDraftMapper;

    // Create a new article by frontend user
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.createArticle(articleMapper.toEntity(articleDTO));
        return new ResponseEntity<>( HttpStatus.OK);
    }
        /***
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getArticleList(@RequestBody PageDTO pageDTO) {
        List<DetailArticleDTO> detailPageDTO = articleService.getArticleByPageDTO(pageDTO);
        return new ResponseEntity<>(detailPageDTO, HttpStatus.OK);
    }
       ***/
    // Update
    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateArticle(@RequestBody ArticleDraftDTO articleDraftDTO) {
        articleService.singleUpdateArticle(articleDraftMapper.toEntity(articleDraftDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/draft/{id}",produces = "application/json")
    public ResponseEntity<?> deleteDraftArticle(@PathVariable("id") Long id) {
        articleService.deleteArticleDraftById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> getArticleAndAllDetails(@PathVariable("id") Long articleId) {
        DetailArticleDTO detailPageDTO = articleService.getArticleAndAllDetails(articleId);

        return new ResponseEntity<>(detailPageDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/postlist",produces = "application/json")
    public ResponseEntity<?> getListPosts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "9") int pageSize) {
        return new ResponseEntity<>( articleService.getPageListPost(page, pageSize),HttpStatus.OK);
    }


}
