package com.bird.app.dto;

import com.bird.common.entity.*;
import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    private Category category;

    private Long categoryId;

//    private Set<Tags> tags = new HashSet<>();

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

    private Set<TagsUseLog> tagsUseLogList = new HashSet<>();

    private Set<CategoryUseLog> categoryUseLogList =new HashSet<>();

    private Set<ArticleAction> actionList = new HashSet<>();

}
