package com.bird.app.controller;

import com.bird.app.dto.TagsDTO;
import com.bird.app.mapper.TagsMapper;
import com.bird.app.service.TagsService;
import com.bird.common.entity.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/tags")
@RequiredArgsConstructor
@Slf4j
public class TagsController {

    private final TagsMapper tagsMapper;
    private final TagsService tagsService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createTags(@RequestBody TagsDTO tagsDTO) {
        return new ResponseEntity<>(tagsService.createTags(tagsMapper.toEntity(tagsDTO)), HttpStatus.CREATED);
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllTagsList(@RequestParam("pageNumber") int pageNumber,
                                                   @RequestParam("pageSize") int pageSize,
                                                   @RequestParam("queryStr") String queryStr) {
        Page<Tags> tagsList = tagsService.getAllTagsList(pageNumber, pageSize, queryStr);
        return new ResponseEntity<>(tagsList, HttpStatus.OK);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<TagsDTO> updateTags(@RequestBody TagsDTO tagsDTO) {
        return new ResponseEntity<>(tagsMapper.toDTO(tagsService.updateTags(tagsMapper.toEntity(tagsDTO))),HttpStatus.OK);
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getTagsById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(tagsService.getTagsById(id), HttpStatus.OK);
    }


    /***
     *
     * TODO 当删除标签的时候，检查taguselog（中间表）是否存在这个id，如果存在，有两种情况
     * 1）与之关联的文章只有这一个标签 taguselog的tagid指向 "其他" 标签 （这个其他标签是不可删除，定义为id=1的）
     * 2）与之关联的文章有多个标签 直接删除记录  tag/taguselog 两个表
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTags(@PathVariable("id") Long id) {
        tagsService.deleteTags(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
