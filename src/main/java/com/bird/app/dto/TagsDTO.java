package com.bird.app.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

/**
 * @author birdyyoung
 * @ClassName:TagDTO
 * @Description:
 * @Date: 12/07/2023 19:24
 * @Version: v1.0
 */
@Data
public class TagsDTO {
    private Long id;
    private String icon;
    private String name;
    private String color;
}
