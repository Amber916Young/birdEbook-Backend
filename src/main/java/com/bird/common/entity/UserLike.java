package com.bird.common.entity;

import com.bird.common.enums.ArticleType;
import com.bird.common.enums.LikeStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/*
用户点赞表设计
 */

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserLike {

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

    @Column
    @CreationTimestamp
    private ZonedDateTime articleTime;

    @Column
    @CreationTimestamp
    private ZonedDateTime modifyTime;

}
