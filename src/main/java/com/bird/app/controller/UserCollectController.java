package com.bird.app.controller;

import com.bird.app.dto.UserCollectDTO;
import com.bird.app.mapper.UserCollectMapper;
import com.bird.app.service.UserCollectService;
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
    public ResponseEntity<?> createUserCollect(UserCollectDTO userCollectDTO) {
        userCollectService.createUserCollect(userCollectMapper.toEntity(userCollectDTO));
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<String> deleteUserCollect(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/updateName", produces = "application/json")
    public ResponseEntity<String> updateCollectName(
            UserCollectDTO userCollectDTO) {
        userCollectService.updateCollectName(userCollectMapper.toEntity(userCollectDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/updateType", produces = "application/json")
    public ResponseEntity<String> updateCollectType(
            UserCollectDTO userCollectDTO) {
        userCollectService.updateCollectType(userCollectMapper.toEntity(userCollectDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getUserCollects", produces = "application/json")
    public ResponseEntity<List<UserCollectDTO>> getUserCollectsByUserId() {
        return ResponseEntity.ok(userCollectMapper.toDTOList(userCollectService.getUserCollectsByUserId()));
    }

    @GetMapping(value = "/getPublicUserCollects/{targetUserId}", produces = "application/json")
    public ResponseEntity<List<UserCollectDTO>> getPublicUserCollectsByUserId(
            @PathVariable("targetUserId") Long targetUserId) {
        return ResponseEntity.ok(userCollectMapper.toDTOList(userCollectService.getPublicUserCollectsByUserId(targetUserId)));

    }
}
