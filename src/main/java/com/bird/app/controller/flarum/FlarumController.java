package com.bird.app.controller.flarum;

import com.bird.app.dto.flarum.TransferImageDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName:FlarumController
 * @Auther: yyj
 * @Description:
 * @Date: 13/08/2023 00:09
 * @Version: v1.0
 */
@Controller
@RequestMapping("/api/flarum")
@RequiredArgsConstructor
@Slf4j
public class FlarumController {
    @SneakyThrows
    @GetMapping( "/dashboard")
    public String dashboard() {
        return "/admin/dashboard/index";
    }

    @GetMapping(value = "/test",produces = "application/json")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("hello",HttpStatus.OK);
    }


    @PostMapping(value = "/generate",produces = "application/json")
    public ResponseEntity<?> getTransferImage(@RequestBody TransferImageDTO transferImageDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
