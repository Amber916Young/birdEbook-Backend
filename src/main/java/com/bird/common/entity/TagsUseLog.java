package com.bird.common.entity;

import com.bird.common.enums.ArticleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Data
@Table(name = "tags_use_log")
@EqualsAndHashCode
public class TagsUseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long tagId;
    @Column
    @Enumerated(EnumType.STRING)
    private ArticleType  articleType;
    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Article article;
}
