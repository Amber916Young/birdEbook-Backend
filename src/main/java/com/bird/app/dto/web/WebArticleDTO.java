package com.bird.app.dto.web;

import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * @ClassName:WebArticleDTO
 * @Auther: yyj
 * @Description:
 * @Date: 22/07/2023 13:53
 * @Version: v1.0
 */
@Data
public class WebArticleDTO {
    private Long id;
    private String title;
    private String description;
    private Long viewCount;
    private Long diggCount;
    private Long commentCount;
    private Long collectCount;
    private String coverImage;
    private ArticleType articleType;
    private ArticleStatus status;
    private String createdBy;
    private Long userId;
    private ZonedDateTime createTime;
    private ZonedDateTime modifyTime;
}
