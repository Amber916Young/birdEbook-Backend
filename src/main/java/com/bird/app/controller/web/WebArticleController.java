package com.bird.app.controller.web;

import com.bird.app.dto.web.DetailPageDTO;
import com.bird.app.dto.web.WebArticleDTO;
import com.bird.app.mapper.ArticleMapper;
import com.bird.app.mapper.WebArticleMapper;
import com.bird.app.service.ArticleService;
import com.bird.common.entity.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/web/articles")
@RequiredArgsConstructor
@Slf4j
public class WebArticleController {

    private final ArticleService articleService;
    private final WebArticleMapper webArticleMapper;

    // Create a new article by frontend user
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createArticle(@RequestBody WebArticleDTO webArticleDTO) {
        Article article = articleService.createArticle(webArticleMapper.toEntity(webArticleDTO));

        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> getArticleAndAllDetails(@PathVariable("id") Long articleId) {
        DetailPageDTO detailPageDTO = articleService.getArticleAndAllDetails(articleId);

        return new ResponseEntity<>(detailPageDTO, HttpStatus.OK);
    }



}
