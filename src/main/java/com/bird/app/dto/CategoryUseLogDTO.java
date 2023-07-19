package com.bird.app.dto;

import com.bird.common.enums.ArticleType;
import lombok.Data;

@Data
public class CategoryUseLogDTO {
    private Long id;
    private Long articleId;
    private Long cateId;
    private ArticleType articleType;
}
