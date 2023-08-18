package com.bird.app.controller;

import com.bird.app.dto.UserCollectDTO;
import com.bird.app.mapper.ArticleActionMapper;
import com.bird.app.mapper.UserCollectMapper;
import com.bird.app.service.UserCollectService;
import com.bird.common.entity.UserCollect;
import com.bird.common.enums.CollectType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: YY
 * @Date: 2023/8/12 11:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/collects")
@RequiredArgsConstructor
@Slf4j
public class UserCollectController {
    private UserCollectService userCollectService;
    private UserCollectMapper userCollectMapper;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<UserCollect> createUserCollect(
            @RequestParam Long userId,
            @RequestParam String collectName,
            @RequestParam Long collectId,
            @RequestParam Long articleId) {
        UserCollect createdCollect =
                userCollectService.createUserCollect(userId, collectName, collectId, articleId);
        return ResponseEntity.ok(createdCollect);
    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteUserCollect(
            @PathVariable Long id,
            @RequestParam Long userId) {
        userCollectService.deleteUserCollect(id, userId);
        return ResponseEntity.ok("删除成功");
    }

    @PutMapping(value = "/updateName", produces = "application/json")
    public ResponseEntity<String> updateCollectName(
            @PathVariable Long id,
            @RequestParam Long userId,
            @RequestParam String collectName) {
        userCollectService.updateCollectName(userId, collectName, id);
        return ResponseEntity.ok("修改成功");
    }

    @PutMapping(value = "/updateType", produces = "application/json")
    public ResponseEntity<String> updateCollectType(
            @PathVariable Long id,
            @RequestParam Long userId,
            @RequestParam CollectType collectType) {
        userCollectService.updateCollectType(userId, id, collectType);
        return ResponseEntity.ok("修改成功");
    }
    @GetMapping(value = "/getUserCollects", produces = "application/json")
    public ResponseEntity<List<UserCollectDTO>> getUserCollectsByUserId(
            @PathVariable Long userId,
            @RequestParam CollectType collectType) {
        List<UserCollectDTO> userCollects = userCollectService.getUserCollectsByUserId(userId, collectType);
        return ResponseEntity.ok(userCollects);
    }

    @GetMapping(value = "/getPublicUserCollects", produces = "application/json")
    public ResponseEntity<List<UserCollectDTO>> getPublicUserCollectsByUserId(
            @PathVariable Long userId,
            @PathVariable Long targetUserId) {
        List<UserCollectDTO> publicCollects = userCollectService.getPublicUserCollectsByUserId(userId, targetUserId);
        return ResponseEntity.ok(publicCollects);
    }
}
