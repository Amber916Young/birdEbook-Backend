package com.bird.common.utils.page;
import lombok.Data;

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
    private long totalPages;
    /**
     * 分页数据
     */
    private Object content;

    public void setTotalPages(long totalSize) {
        long totalno = totalSize;
        if (totalSize % pageSize == 0) {
            totalno = totalSize / pageSize;
        } else {
            totalno = totalSize / pageSize + 1;
        }
        this.totalPages = totalno;
    }
}
