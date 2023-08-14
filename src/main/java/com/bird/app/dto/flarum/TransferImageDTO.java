package com.bird.app.dto.flarum;

import lombok.Data;

/**
 * @author birdyyoung
 * @ClassName:TransferImageDTO
 * @Auther: yyj
 * @Description:
 * @Date: 12/08/2023 19:40
 * @Version: v1.0
 */
@Data
public class TransferImageDTO {
    private Long id;
    private String username;
    private String content;
    private String avatar;
    private String author;
    private String postDate;
}
