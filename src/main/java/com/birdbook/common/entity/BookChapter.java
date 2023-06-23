package com.birdbook.common.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BookChapter  implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NotNull
    @Column
    private Long bookId;

    @NotNull
    @Column
    private Long chapterNumber;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    private String chapterName;

    @NotNull
    @Column
    private String content;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;
    @Column
    @LastModifiedDate
    private ZonedDateTime modifyTime;

    public boolean isNew() {
        return id == null;
    }

}
