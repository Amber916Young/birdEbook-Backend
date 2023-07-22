package com.bird.app.dto;

import com.bird.common.enums.ArticleType;
import lombok.Data;

/**
 * @ClassName:LikeDTO
 * @Auther: yyj
 * @Description:
 * @Date: 20/07/2023 20:52
 * @Version: v1.0
 */
@Data
public class LikeDTO {
    private Long id;
    private Long userId;
    private Long articleId;
    private ArticleType articleType;
}
