package com.bird.app.dto;

import com.bird.common.enums.ArticleType;
import com.bird.common.enums.CollectType;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.ZonedDateTime;

/**
 * @Author: YY
 * @Date: 2023/8/11 17:33
 * @Version 1.0
 */
@Data
public class UserCollectDTO {
    private Long id;
    private Long userId;
    private Long articleId;
    private String collectName;
    private ArticleType articleType;
    private CollectType collectType;
}
