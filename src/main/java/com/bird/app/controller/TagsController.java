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

    // Create
    @PostMapping(produces = "application/json")
    public ResponseEntity<TagsDTO> createTags(@RequestBody TagsDTO tagsDTO) {
        return new ResponseEntity<>(tagsMapper.toDTO(tagsService.createTags(tagsMapper.toEntity(tagsDTO))), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all",produces = "application/json")
    public ResponseEntity<?> getAllTagsListWithoutPage() {
        return new ResponseEntity<>(tagsMapper.toDTOList(tagsService.getAllTagsListWithoutPage()), HttpStatus.OK);
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
    public ResponseEntity<TagsDTO> getTagsById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(tagsMapper.toDTO(tagsService.getTagsById(id)), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTags(@PathVariable("id") Long id) {
        tagsService.deleteTags(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
