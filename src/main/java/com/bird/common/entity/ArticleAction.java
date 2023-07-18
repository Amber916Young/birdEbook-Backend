package com.bird.common.entity;

import com.bird.common.enums.OperationType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * @author birdyyoung
 * @ClassName:Logger
 * @Description:
 * @Date: 01/07/2023 19:27
 * @Version: v1.0
 */

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ArticleAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NotNull
    private Long userId;

    @Column
    @NotNull
    private Long articleId;

    @NotNull
    @Column
    private String createdBy;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column
    private OperationType   operationType;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;
}
