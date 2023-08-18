package com.bird;

import com.bird.app.dto.UserCollectDTO;
import com.bird.common.config.exception.BadRequestException;
import com.bird.common.config.exception.ErrorReasonCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@SpringBootTest
class BirdbookApplicationTests {

    @Test
    void contextLoads() {
    }
}