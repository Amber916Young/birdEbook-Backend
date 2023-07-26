package com.bird.app.controller;

import com.bird.app.dto.LikeDTO;
import com.bird.app.mapper.UserLikeMapper;
import com.bird.app.service.UserLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
@Slf4j
public class UserLikeController {

    private final UserLikeService userLikeService;
    private final UserLikeMapper userLikeMapper;


    @PostMapping(produces = "application/json")
    public ResponseEntity<LikeDTO> saveOrCancelLike(@RequestBody LikeDTO likeDTO) {
        userLikeService.createOrCancelLikesWIKI(userLikeMapper.toEntity(likeDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

