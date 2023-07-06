package com.bird.app.dto;

import lombok.Data;

@Data
public class BookChapterDTO {
    private Long id;
    private Long bookId;
    private Long chapterNumber;
    private String chapterName;
    private String content;
}
