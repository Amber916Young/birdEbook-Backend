package com.bird.common.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

/**
 * @author birdyyoung
 * @ClassName:Tag
 * @Description:
 * @Date: 12/07/2023 19:25
 * @Version: v1.0
 *   name: string;
 *     icon: string; // fa 格式
 *     cateId: number; // further dev
 *     createTime: Date;
 *   }
 */

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String icon;

    @Column
    private String name;

    @Column
    private String color;
    @Column
    private Long cateId;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;


}
