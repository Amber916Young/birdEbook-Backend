package com.bird.app.controller;

import com.bird.app.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author birdyyoung
 * @ClassName:ImageController
 * @Description:
 * @Date: 09/07/2023 18:41
 * @Version: v1.0
 */

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/upload/wiki")
    public ResponseEntity<?> uploadCompanyLogo(@RequestParam("file") MultipartFile file) throws IOException {
        String url  = imageService.uploadWikiImages(file);

        return new ResponseEntity<>(url, HttpStatus.OK);
    }

}
