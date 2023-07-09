package com.bird.common.entity;

import com.bird.common.enums.OperationType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class WikiAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NotNull
    private Long userId;

    @Column
    @NotNull
    private Long wikiId;

    @NotNull
    @Column
    private String username;

    @NotNull
    @Column
    private String   operationType;


    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;
}
