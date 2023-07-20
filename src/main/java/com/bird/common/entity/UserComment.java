package com.bird.common.entity;

/*
用户评论表
 */

import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.DateTime;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    //自增主键
    private long commentId;

    @NotNull
    @Column
    //评论用户id
    private long fromUserId;

    @NotNull
    @Column
    //评论目标用户id
    private long toUserId;

    @NotNull
    @Column
    //作品的id
    private Integer composeId;

    @NotNull
    @Column
    //作品类型，1.维基百科，2.小说,3.帖子
    private Integer composeType;

    @NotNull
    @Column
    //评论状态，1.禁止评论，2.允许评论
    private Integer commentStatus;

    @NotNull
    @Column
    //评论的内容
    private String commentContent;

    @NotNull
    @Column
    @CreationTimestamp
    //评论时间
    private ZonedDateTime commentCreateTime;

    @Column
    @LastModifiedDate
    private ZonedDateTime modifyTime;

}
