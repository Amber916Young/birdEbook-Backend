package com.bird.common.entity;

/*




回复表

 */

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class UserReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    //自增主键,回复唯一标识符
    private long replyId;

    @NotNull
    @Column
    //被回复的评论的ID(外键，关联到UserComment表的id)
    private long commentId ;

    @NotNull
    @Column
    // 回复发起用户的ID(外键，关联到Users表的user_id)
    private long replyUserId;

    @NotNull
    @Column
    //被回复的目标用户的IDt(外键，关联到Users表的user_id):
    private long toReplyUserId;

    @NotNull
    @Column
    //replyContent: 回复的内容
    private String replyContent;


    @NotNull
    @Column
    @CreationTimestamp
    // 回复时间
    private ZonedDateTime replyTime;

    @NotNull
    @Column
    //更新时间
    private ZonedDateTime replyUpdateTime;

}
