package com.bird.app.dto.web;

import com.bird.common.entity.ArticleAction;
import com.bird.common.entity.CategoryUseLog;
import com.bird.common.entity.TagsUseLog;
import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class WebArticleDTO {
    private Long id;
    private String title;
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
    private Set<TagsUseLog> tagsUseLogList = new HashSet<>();
    private CategoryUseLog categoryUseLogList;
    private Set<ArticleAction> actionList = new HashSet<>();
}