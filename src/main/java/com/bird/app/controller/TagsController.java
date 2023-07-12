package com.bird.app.controller;

import com.bird.app.mapper.BookMapper;
import com.bird.app.mapper.TagsMapper;
import com.bird.app.service.BookService;
import com.bird.app.service.TagsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author birdyyoung
 * @ClassName:WebCategoryController
 * @Description:
 * @Date: 10/07/2023 14:46
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
@Slf4j
public class TagsController {

    private final TagsMapper tagsMapper;
    private final TagsService tagsService;





}
