package com.bird.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @ClassName:transferController
 * @Auther: yyj
 * @Description:
 * @Date: 12/08/2023 19:37
 * @Version: v1.0
 */

@RestController
@RequestMapping("/api/transfer")
@RequiredArgsConstructor
@Slf4j
public class TransferController {


    @PostMapping(produces = "application/json")
    public ResponseEntity<?> getTransferImage() {
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
