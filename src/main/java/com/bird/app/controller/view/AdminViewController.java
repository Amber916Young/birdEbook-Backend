package com.bird.app.controller.view;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
@Slf4j
public class AdminViewController {
    @SneakyThrows
    @GetMapping( "/dashboard")
    synchronized public String dashboard() {
        return "/admin/dashboard/index";
    }


    @SneakyThrows
    @GetMapping( "/article")
    synchronized public String article() {
        return "/admin/article/index";
    }
}
