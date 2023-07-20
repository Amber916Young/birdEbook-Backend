package com.bird.app.controller.web;

import com.bird.app.dto.DetailArticleDTO;
import com.bird.app.service.ArticleService;
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

    // Create a new article by frontend user
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createArticle(@RequestBody  DetailArticleDTO detailArticleDTO) {
        DetailArticleDTO article = articleService.createArticle(detailArticleDTO);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> getArticleAndAllDetails(@PathVariable("id") Long articleId) {
        DetailArticleDTO detailPageDTO = articleService.getArticleAndAllDetails(articleId);

        return new ResponseEntity<>(detailPageDTO, HttpStatus.OK);
    }



}
