package com.bird.common.entity;

import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

/**
 * @author birdyyoung
 * @ClassName:WikiArticle
 * @Description:
 * @Date: 01/07/2023 18:46
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Article implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String title;
    @NotNull
    @Column
    private Long categoryId;

    private String tagIds;

    @Column
    private String description;

    @Column
    private Long viewCount;

    @Column
    private Long diggCount;

    @Column
    private Long commentCount;
    @Column
    private Long collectCount;

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
    private Long version;

    @Column
    @NotNull
    private String createdBy;
    @Column
    @NotNull
    private Long userId;
    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

    @Column
    @LastModifiedDate
    private ZonedDateTime modifyTime;


    @OneToMany(mappedBy = "articleId", fetch = FetchType.EAGER, cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<TagsUseLog> tagsUseLogList = new HashSet<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<CategoryUseLog> categoryUseLogList = new HashSet<>();

    @OneToMany(mappedBy = "articleId", fetch = FetchType.LAZY, cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ArticleAction> actionList = new HashSet<>();

    @Override
    public boolean isNew() {
        return id == null;
    }

}
