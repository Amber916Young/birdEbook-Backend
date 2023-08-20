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
    private final UserCollectService userCollectService;
    private final UserCollectMapper userCollectMapper;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createUserCollect(UserCollectDTO userCollectDTO) {
        userCollectService.createUserCollect(userCollectMapper.toEntity(userCollectDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteUserCollect(@PathVariable("id") Long id) {
        userCollectService.deleteUserCollect(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/updateName", produces = "application/json")
    public ResponseEntity<?> updateCollectName(
            UserCollectDTO userCollectDTO) {
        userCollectService.updateCollectName(userCollectMapper.toEntity(userCollectDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/updateType", produces = "application/json")
    public ResponseEntity<?> updateCollectType(
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
