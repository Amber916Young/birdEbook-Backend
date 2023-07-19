package com.bird.common.entity;

import com.bird.common.enums.ArticleType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @ClassName:CategoryTypeUseLog
 * @Auther: yyj
 * @Description:
 * @Date: 17/07/2023 16:31
 * @Version: v1.0
 */

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class TagsUseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long articleId;
    @Column
    private Long tagId;
    @Column
    @Enumerated(EnumType.STRING)
    private String  articleType;
    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;
}
