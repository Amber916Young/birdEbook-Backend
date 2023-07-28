package com.bird.app.dto;/*
 */

import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ListPostDTO {
    private Long id;

    private Long userId;

    private String title;

    private String content;

    private ArticleType articleType;

    private ArticleStatus status;

    private ZonedDateTime createTime;
}
