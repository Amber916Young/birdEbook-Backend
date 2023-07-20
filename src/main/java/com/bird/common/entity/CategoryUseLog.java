package com.bird.common.entity;

import com.bird.common.enums.ArticleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @ClassName:CategoryTypeUseLog
 * @Author: yyj
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
public class CategoryUseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long cateId;
    @Column
    @Enumerated(EnumType.STRING)
    private ArticleType articleType;

    @OneToOne
    @JoinColumn(name = "article_id")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Article article;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;
}
