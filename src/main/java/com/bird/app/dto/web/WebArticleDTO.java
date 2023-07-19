package com.bird.app.dto.web;

import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class WebArticleDTO {
    private Long id;

    private String title;

    private Long categoryId;

    private String tagIds;

    private String description;

    private Long viewCount;

    private Long diggCount;

    private Long commentCount;

    private Long collectCount;

    private String content;

    private String coverImage;

    private ArticleType articleType;

    private ArticleStatus status;

    private Long version;

    private String createdBy;

    private Long userId;

    private ZonedDateTime createTime;

    private ZonedDateTime modifyTime;
}