package com.bird.app.controller.web;

import com.bird.app.mapper.TagsMapper;
import com.bird.app.service.TagsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author birdyyoung
 * @ClassName:WebCategoryController
 * @Description:
 * @Date: 10/07/2023 14:46
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/web/tags")
@RequiredArgsConstructor
@Slf4j
public class WebTagsController {

    private final TagsMapper tagsMapper;
    private final TagsService tagsService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllTagsList() {
        return new ResponseEntity<>(tagsMapper.toDTOList(tagsService.getAllTagsListWithoutPage()), HttpStatus.OK);
    }


}
