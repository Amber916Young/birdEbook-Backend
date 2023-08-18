package com.bird.common.utils;

import com.bird.common.config.exception.BadRequestException;
import com.bird.common.config.exception.ErrorReasonCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @ClassName:ImageManager
 * @Auther: yyj
 * @Description:
 * @Date: 09/07/2023 19:37
 * @Version: v1.0
 */
@Service
public class ImageManager {

    @Value("${application.client_id}")
    private String clientId;

    @Value("${application.client_secret}")
    private String clientSecret;

    @Value("${application.auth_url}")
    private String authUrl;
    @Value("${application.token_url}")
    private String tokenUrl;


    @SneakyThrows
    public String uploadImage(MultipartFile file) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.imgur.com/3/image");
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Client-ID " + clientId);
//        String accessToken= requestToken();
//        httpPost.setHeader("Authorization", "Bearer " + accessToken);

        byte[] imageData = file.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        StringEntity requestEntity = new StringEntity("{\"image\": \"" + base64Image + "\"}",
                ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(httpPost);
        String responseJson = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        httpClient.close();

        JSONObject jb = new JSONObject(responseJson);
        if (jb.has("status") && jb.has("success")) {
            boolean success = jb.getBoolean("success");
            int status = jb.getInt("status");
            if (success && status == 200) {
                System.out.println(responseJson);
                JSONObject dataJb = jb.getJSONObject("data");
                return dataJb.getString("link");
            }
            throw new BadRequestException(ErrorReasonCode.Upload_Image_invalid);

        }
        throw new BadRequestException(ErrorReasonCode.Upload_Image_invalid);


    }
    public String requestToken() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        String encodedRedirectUrl = URLEncoder.encode("YOUR_REDIRECT_URL", "UTF-8");
        String authorizationUrl = authUrl + "?client_id=" + clientId +
                "&response_type=code" +
                "&state=YOUR_STATE" +
                "&redirect_uri=" + encodedRedirectUrl;

        java.awt.Desktop.getDesktop().browse(java.net.URI.create(authorizationUrl));
        String authorizationCode = "YOUR_AUTHORIZATION_CODE";
        HttpPost httpPost = new HttpPost(tokenUrl);
        StringEntity requestBody = new StringEntity("client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&grant_type=refresh_token" +
                "&code=" + authorizationCode);
        httpPost.setEntity(requestBody);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        HttpResponse response = httpClient.execute(httpPost);
        String responseBody = EntityUtils.toString(response.getEntity());
        String accessToken = responseBody.split("\"")[3];

        return accessToken;
    }

//    private String requestToken() {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
//        requestBody.add("client_id", clientId);
//        requestBody.add("client_secret", clientSecret);
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
//        ResponseEntity<AccessTokenResponse> responseEntity = restTemplate.postForEntity(tokenUrl, requestEntity, AccessTokenResponse.class);
//        AccessTokenResponse accessTokenResponse = responseEntity.getBody();
//        String accessToken = accessTokenResponse.getAccessToken();
//        return accessToken;
//    }
}
@Data
 class AccessTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private int expiresIn;

}
