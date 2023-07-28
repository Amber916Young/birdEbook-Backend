package com.bird.app.controller;

import com.bird.app.dto.ListPostDTO;
import com.bird.app.mapper.ListPostMapper;
import com.bird.app.service.ListPostService;
import com.bird.common.entity.Article;
import com.bird.common.entity.ListPost;
import com.bird.common.repository.ListPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
// 处理分页逻辑，根据page和pageSize获取相应的文章列表

        Page<ListPostDTO> listPosts = listPostService.getPageListPost(page, pageSize);

        if (listPosts.isEmpty()) {
            // 列表为空
            String message = "没有找到相关文章";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {

            // 对文章列表进行分页处理
            List<ListPostDTO> listPostsDTO = listPosts.getContent();
            int totalArticles = (int) listPosts.getTotalElements();
            // 返回分页后的文章列表及总文章数信息
            return new ResponseEntity<>(listPosts, HttpStatus.OK);

        }

 */


@RestController
@RequestMapping("/api/postlist")
@RequiredArgsConstructor
@Slf4j
public class ListPostController {

    private final ListPostService listPostService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getListPosts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {

        Sort sort = Sort.by(sortDirection, "createTime");

        Page<ListPostDTO> listPostPage =
                listPostService.getPageListPost(page, pageSize, sort);

        if (listPostPage.getContent().isEmpty()) {
            // 列表为空
            String message = "没有找到相关文章";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        return ResponseEntity.ok(listPostPage);
    }
}
