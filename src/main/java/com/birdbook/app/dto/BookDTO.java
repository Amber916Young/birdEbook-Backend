package com.birdbook.app.dto;

import com.birdbook.common.enums.BookStatus;
import lombok.Data;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author birdyyoung
 */
@Data
public class BookDTO {
    private Long id;

    private String bookName;

    private String author;

    private String avatar_url;

    private long userId;

    private String description;
    private String category;
    private String tags;

    private BookStatus status;

    private ZonedDateTime createTime;

    private ZonedDateTime publishTime;

    private ZonedDateTime modifyTime;

    private List<BookChapterDTO> bookChapters;
}
