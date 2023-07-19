package com.bird.app.controller.web;

import com.bird.app.dto.web.WebArticleDTO;
import com.bird.app.mapper.ArticleMapper;
import com.bird.app.mapper.WebArticleMapper;
import com.bird.app.service.WebArticleService;
import com.bird.common.entity.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/articles")
@RequiredArgsConstructor
@Slf4j
public class WebArticleController {

    private final WebArticleService webArticleService;
    private final WebArticleMapper webArticleMapper;

    // Create a new article by frontend user
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createArticle(@RequestBody WebArticleDTO webArticleDTO) {
        Article article = webArticleService.createArticle(webArticleMapper.toEntity(webArticleDTO));

        return new ResponseEntity<>(article, HttpStatus.OK);
    }



}