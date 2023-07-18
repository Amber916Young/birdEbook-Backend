package com.bird.common.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

/**
 * @author birdyyoung
 * @ClassName:WikiArticle
 * @Description:
 * @Date: 01/07/2023 18:46
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class WikiArticle implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String title;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String categoryType;

    @Column
    private String description;

    @Column
    private String tags;
    @Column
    private Long views;
    @NotNull
    @Column
    private String content;

    @Column
    private String wikiImageUrl;

    @Column
    private boolean status;

    @Column
    @NotNull
    private String createdBy;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

    @Column
    @LastModifiedDate
    private ZonedDateTime modifyTime;

    @Override
    public boolean isNew() {
        return id == null;
    }

}
