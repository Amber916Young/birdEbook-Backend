package com.bird.app.service;

import com.bird.common.utils.ImageManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

/**
 * @ClassName:ImageService
 * @Auther: yyj
 * @Description:
 * @Date: 09/07/2023 18:48
 * @Version: v1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ImageService {

    private final ImageManager imageManager;

    public String uploadWikiImages(MultipartFile file) {

        String imageUrl = imageManager.uploadImage(file);

        //TODO

        return imageUrl;
    }


}
