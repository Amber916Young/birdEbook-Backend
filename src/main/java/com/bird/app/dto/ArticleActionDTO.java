package com.bird.app.dto;

import com.bird.common.enums.OperationType;
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
public class ArticleActionDTO {
    private Long id;
    private Long userId;
    private Long articleId;
    private String createdBy;
    private OperationType operationType;
    private ZonedDateTime createTime;
}
