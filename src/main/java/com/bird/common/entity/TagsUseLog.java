package com.bird.common.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @ClassName:CategoryTypeUseLog
 * @Auther: yyj
 * @Description:
 * @Date: 17/07/2023 16:31
 * @Version: v1.0
 */

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class TagsUseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long tagId;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Article article;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

}
