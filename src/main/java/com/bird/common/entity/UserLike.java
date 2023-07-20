package com.bird.common.entity;

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
    //自增主键
    private long likeId;

    @NotNull
    @Column
    //点赞用户的id
    private long likedByUserId ;

    @NotNull
    @Column
    //被点赞用户id
    private long likedUserId;

    @NotNull
    @Column
    //被点赞的作品的id
    private long likeComposeId;

    @NotNull
    @Column
    //点赞作品的类型，1.评论点赞，2.帖子点赞
    private Integer likeComposeType;

    @NotNull
    @Column
    //点赞状态，0表示取消或者未点赞，1表示点赞
    private LikeStatus likeStatus;

    @NotNull
    @Column
    @CreationTimestamp
    //点赞时间
    private ZonedDateTime likeCreateTime;

    @NotNull
    @Column
    //更新时间
    private ZonedDateTime likeUpdateTime;

}
