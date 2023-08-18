package com.bird.common.entity;

import com.bird.common.enums.ArticleType;
import com.bird.common.enums.CollectType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.ZonedDateTime;
import com.sun.istack.NotNull;

import javax.persistence.*;


/**
 * @Author: YY
 * @Date: 2023/8/14 15:12
 * @Version 1.0
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
    private Long articleId;

    @NotNull
    @Column
    private String collectName;

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


