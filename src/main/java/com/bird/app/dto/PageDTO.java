package com.bird.app.dto;

import lombok.Data;

/**
 * @ClassName:PageDTO
 * @Auther: yyj
 * @Description:
 * @Date: 08/07/2023 11:19
 * @Version: v1.0
 */
@Data
public class PageDTO {
    private int pageNumber;
    private int pageSize;
    private String queryStr;
}
