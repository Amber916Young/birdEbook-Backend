package com.bird.app.controller;

import com.bird.app.dto.ArticleActionDTO;
import com.bird.app.mapper.ArticleActionMapper;
import com.bird.app.service.ArticleActionService;
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
public class ArticleActionController {

    private final ArticleActionService articleActionService;

    private final ArticleActionMapper articleActionMapper;


    // Create
    @PostMapping(produces = "application/json")
    public ResponseEntity<ArticleActionDTO> createWikiAction(@RequestBody ArticleActionDTO articleActionDTO) {
        return new ResponseEntity<>(articleActionMapper.toDTO(articleActionService.createWikiAction(articleActionMapper.toEntity(articleActionDTO))), HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllWikiArticleList(@RequestParam("pageNumber") int pageNumber,
                                                   @RequestParam("pageSize") int pageSize,
                                                   @RequestParam("queryStr") String queryStr) {


        Page<ArticleAction> articles = articleActionService.getAllWikiActionList(pageNumber, pageSize, queryStr);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ArticleActionDTO> getWikiActionById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(articleActionMapper.toDTO(articleActionService.getWikiActionById(id)), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWikiAction(@PathVariable("id") Long id) {
        articleActionService.deleteWikiAction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
