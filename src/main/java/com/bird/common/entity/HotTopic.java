package com.bird.common.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @ClassName:HotTopic
 * @Auther: yyj
 * @Description:
 * @Date: 15/07/2023 12:28
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class HotTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long topicId;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

}
