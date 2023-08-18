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
@Data
@Table(name = "article")
@EqualsAndHashCode
public class Article{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private Long userId;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String title;

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


    @Column(name = "create_time")
    @CreationTimestamp
    private ZonedDateTime createTime;

    @Column
    @LastModifiedDate
    private ZonedDateTime modifyTime;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<TagsUseLog> tagsUseLogList = new ArrayList<>();

    @OneToOne(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private CategoryUseLog categoryUseLog;


}
