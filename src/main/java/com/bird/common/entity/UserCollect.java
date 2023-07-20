package com.bird.common.entity;

import com.bird.common.enums.ArticleType;
import com.bird.common.enums.CollectType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

/*

收藏表
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserCollect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private Long userId;

    @NotNull
    @Column
    private String collectName;

    @NotNull
    @Column
    private Long articleId;

    @NotNull
    @Column
    private ArticleType articleType;

    @NotNull
    @Column
    private CollectType collectType;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

    @Column
    @CreationTimestamp
    private ZonedDateTime modifyTime;
}


