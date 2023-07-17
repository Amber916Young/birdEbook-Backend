package com.bird.app.dto;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * @ClassName:WikiArticleDTO
 * @Author: yyj
 * @Description:
 * @Date: 01/07/2023 20:41
 * @Version: v1.0
 */
@Data
public class WikiArticleDTO {
    private Long id;
    private String title;
    private String categoryType;
    private String categoryTypeValue;
    private String tagsValue;
    private String tags;
    private String content;
    private String wikiImageUrl;
    private String description;
    private Long views;
    private boolean status;
    private String createdBy;
    private ZonedDateTime createTime;
}
