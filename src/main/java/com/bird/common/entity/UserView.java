package com.bird.common.entity;


import com.bird.common.enums.ArticleType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/*
用户浏览表
 */


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private Long userId;

    @NotNull
    @Column
    private Long articleId;

    @NotNull
    @Column
    private ArticleType articleType;

    @CreationTimestamp
    private ZonedDateTime createTime;

}

