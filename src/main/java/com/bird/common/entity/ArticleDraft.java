package com.bird.common.entity;

import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author birdyyoung
 * @ClassName:WikiArticle
 * @Description:
 * @Date: 01/07/2023 18:46
 * @Version: v1.0
 */
@Entity
@Data
@Table(name = "article_draft")
@EqualsAndHashCode
public class ArticleDraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long articleId;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String title;

    @Column
    private String description;

    @NotNull
    @Column
    private String content;

    @Column
    private String coverImage;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private ArticleType articleType;

    @Column
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @Column
    @NotNull
    private String createdBy;
    @Column
    private Long cateId;
    @Column
    private String tagIds;
    @Column
    @NotNull
    private Long userId;
    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

    @Column
    @LastModifiedDate
    private ZonedDateTime modifyTime;

}
