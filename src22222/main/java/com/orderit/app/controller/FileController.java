package com.orderit.app.controller;

import com.orderit.app.service.FileService;
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

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload/company-logos")
    public ResponseEntity<?> uploadCompanyLogo(@RequestParam("file") MultipartFile file) throws IOException {
        String url  = fileService.uploadCompanyLogo(file);

        return new ResponseEntity<>(url, HttpStatus.OK);
    }


    @PostMapping(value = "/upload/product-images")
    public ResponseEntity<?> uploadProductImages(@RequestParam("file") MultipartFile file, @RequestParam("oldUrl") String oldUrl) throws IOException {
        String url  = fileService.uploadProductImage(file,oldUrl);

        return new ResponseEntity<>(url, HttpStatus.OK);
    }
}
