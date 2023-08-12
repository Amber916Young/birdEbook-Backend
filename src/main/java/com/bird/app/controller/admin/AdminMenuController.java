package com.bird.app.controller.admin;

import com.bird.app.mapper.admin.AdminMenuMapper;
import com.bird.app.service.AdminMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/menu")
@RequiredArgsConstructor
@Slf4j
public class AdminMenuController {

    private final AdminMenuService adminMenuService;
    private final AdminMenuMapper adminMenuMapper;


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllMenuTree() {
        return new ResponseEntity<>(adminMenuService.findAllMenu(), HttpStatus.OK);
    }
}
