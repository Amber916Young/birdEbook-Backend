package com.bird.common.entity;/*
 */

import com.bird.common.enums.ArticleStatus;
import com.bird.common.enums.ArticleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
@Data
@Table
@EqualsAndHashCode
public class ListPost {

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

    @NotNull
    @Column
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private ArticleType articleType;

    @Column
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;
}
