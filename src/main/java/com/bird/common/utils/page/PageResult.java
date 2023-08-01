package com.bird.common.utils.page;

import lombok.Data;

import java.util.List;

/**
 * @ClassName:PageDTO
 * @Auther: yyj
 * @Description:
 * @Date: 21/07/2023 21:17
 * @Version: v1.0
 */
@Data
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private long totalPages;
    /**
     * 分页数据
     */
    private List<?> content;
}
