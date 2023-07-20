package com.bird.app.controller;/*
 */

import com.bird.app.dto.UserLikeDTO;
import com.bird.app.service.UserLikeService;
import com.bird.common.enums.LikeStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/userlikes")
public class UserLikeController {

    private final UserLikeService userLikeService;


    @PostMapping
    public ResponseEntity<UserLikeDTO> saveOrUpdateUserLike(@RequestBody UserLikeDTO userLikeDTO) {
        UserLikeDTO savedUserLike = userLikeService.saveOrUpdate(userLikeDTO);
        return ResponseEntity.ok(savedUserLike);
    }


    @PutMapping("/{likeId}/{likeStatus}")
    public ResponseEntity<UserLikeDTO> updateLikeStatus(@PathVariable long likeId, @PathVariable LikeStatus likeStatus) {
        UserLikeDTO updatedUserLike = userLikeService.updateLikeStatus(likeId, likeStatus);
        if (updatedUserLike != null) {
            return ResponseEntity.ok(updatedUserLike);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

