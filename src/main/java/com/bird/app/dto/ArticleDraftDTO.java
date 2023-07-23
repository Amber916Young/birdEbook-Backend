package com.bird.app.dto;

import com.bird.common.entity.CategoryUseLog;
import com.bird.common.entity.TagsUseLog;
import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import com.bird.common.enums.AuditsType;
import lombok.Data;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:ArticleDraftDTO
 * @Auther: yyj
 * @Description:
 * @Date: 23/07/2023 09:42
 * @Version: v1.0
 */
@Data
public class ArticleDraftDTO {
    private Long id;
    private Long articleId;
    private String title;
    private String description;
    private String content;
    private String coverImage;
    private ArticleType articleType;
    private ArticleStatus status;
    private Long cateId;
    private String tagIds;
    private AuditsType auditsType;
    private String createdBy;
    private Long userId;
    private ZonedDateTime createTime;
    private ZonedDateTime modifyTime;
}
