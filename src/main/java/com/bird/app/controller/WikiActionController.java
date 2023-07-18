package com.bird.app.controller;

import com.bird.app.dto.WikiActionDTO;
import com.bird.app.mapper.WikiActionMapper;
import com.bird.app.service.WikiActionService;
import com.bird.common.entity.ArticleAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName:WikiActionController
 * @Auther: yyj
 * @Description:
 * @Date: 06/07/2023 21:55
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/wikiactions")
@RequiredArgsConstructor
@Slf4j
public class WikiActionController {

    private final WikiActionService wikiActionService;

    private final WikiActionMapper wikiActionMapper;


    // Create
    @PostMapping(produces = "application/json")
    public ResponseEntity<WikiActionDTO> createWikiAction(@RequestBody WikiActionDTO wikiActionDTO) {
        return new ResponseEntity<>(wikiActionMapper.toDTO(wikiActionService.createWikiAction(wikiActionMapper.toEntity(wikiActionDTO))), HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllWikiArticleList(@RequestParam("pageNumber") int pageNumber,
                                                   @RequestParam("pageSize") int pageSize,
                                                   @RequestParam("queryStr") String queryStr) {


        Page<ArticleAction> articles = wikiActionService.getAllWikiActionList(pageNumber, pageSize, queryStr);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<WikiActionDTO> getWikiActionById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(wikiActionMapper.toDTO(wikiActionService.getWikiActionById(id)), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWikiAction(@PathVariable("id") Long id) {
        wikiActionService.deleteWikiAction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
