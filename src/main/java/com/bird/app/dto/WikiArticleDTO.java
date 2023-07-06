package com.bird.app.dto;

import com.bird.common.enums.CategoryType;
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
    private CategoryType category;
    private String tags;
    private String content;
    private String wikiImageUrl;
    private String desc;
    private ZonedDateTime createTime;
}
