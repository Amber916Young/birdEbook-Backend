package com.bird.common.entity;


import lombok.*;

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
    private long viewId;

    @NotNull
    @Column
    // 用户ID
    private long viewUId;

    @NotNull
    @Column
    // 浏览内容ID
    private long ViewContentId;

    @NotNull
    @Column
    //作品类型，1.维基百科，2.小说
    private Integer contentType;

    @NotNull
    @Column
    // 浏览时间
    private ZonedDateTime viewTime;

}

