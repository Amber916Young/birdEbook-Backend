package com.bird.common.entity;



import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

/*

收藏表
 */

public class UserCollect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long collectId;

    @NotNull
    @Column
    // 用户ID
    private long collUserId;


    @NotNull
    @Column
    // 收藏夹名称
    private String collectName;


    @NotNull
    @Column
    // 收藏作品ID
    private long composeId;

    @NotNull
    @Column
    //收藏作品类型，1.维基百科，2.小说，3帖子
    private Integer composeType;

    @NotNull
    @Column
    // 收藏夹类型：0 - 公开，1 - 私密
    private Integer collectType;

    @NotNull
    @Column
    @CreationTimestamp
    // 创建时间
    private ZonedDateTime collectCreateTime;

    @NotNull
    @Column
    // 更新时间
    private ZonedDateTime collectUpdateTime;
    }


