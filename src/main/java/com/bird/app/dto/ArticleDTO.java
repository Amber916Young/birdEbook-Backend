package com.bird.app.dto;

import com.bird.common.entity.*;
import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName:WikiArticleDTO
 * @Author: yyj
 * @Description:
 * @Date: 01/07/2023 20:41
 * @Version: v1.0
 */
@Data
public class ArticleDTO {
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
    private List<TagsUseLog> tagsUseLogList = new ArrayList<>();
    private CategoryUseLog categoryUseLog;
}
