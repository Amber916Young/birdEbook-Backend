package com.bird.app.dto;

import lombok.Data;

/**
 * @ClassName:PageDTO
 * @Auther: yyj
 * @Description:
 * @Date: 21/07/2023 21:17
 * @Version: v1.0
 */
@Data
public class PageDTO {
    private int pageNo;
    private int pageCount;
    private String keyword;
}
