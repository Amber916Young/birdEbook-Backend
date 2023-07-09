package com.bird.app.controller;

import com.bird.app.dto.PageDTO;
import com.bird.app.dto.WikiArticleDTO;
import com.bird.app.mapper.WikiArticleMapper;
import com.bird.app.service.WikiArticleService;
import com.bird.common.entity.WikiArticle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author birdyyoung
 * @ClassName:WikiController
 * @Description:
 * @Date: 01/07/2023 19:48
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/wikis")
@RequiredArgsConstructor
@Slf4j
public class WikiController {
    private final WikiArticleService wikiArticleService;
    private final WikiArticleMapper wikiArticleMapper;


    // Create a new article
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createArticle(@RequestBody WikiArticleDTO wikiArticleDTO) {
        WikiArticle article = wikiArticleService.createArticle(wikiArticleMapper.toEntity(wikiArticleDTO));
        return new ResponseEntity<>(wikiArticleMapper.toDTO(article), HttpStatus.OK);
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllWikiArticleList(@RequestParam("pageNumber") int pageNumber,
                                                   @RequestParam("pageSize") int pageSize,
                                                   @RequestParam("queryStr") String queryStr) {


        Page<WikiArticle> articles = wikiArticleService.getAllWikiArticleList(pageNumber, pageSize, queryStr);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    // Read an article by ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<WikiArticleDTO> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(wikiArticleMapper.toDTO(wikiArticleService.getArticleById(id)));
    }

    // Update an existing article
    @PutMapping(produces = "application/json")
    public ResponseEntity<WikiArticleDTO> updateArticle( @RequestBody WikiArticleDTO wikiArticleDTO) {
        return ResponseEntity.ok(wikiArticleMapper.toDTO(wikiArticleService.updateArticleById( wikiArticleMapper.toEntity(wikiArticleDTO))));

    }

    // Delete an article by ID
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        wikiArticleService.deleteArticleById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
