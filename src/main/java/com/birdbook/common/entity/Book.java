package com.birdbook.common.entity;

import com.birdbook.common.enums.BookStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Book  implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String bookName;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String author;

    @Size(min = 1, max = 200)
    @Column(length = 200)
    private String image_url;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(length = 30, nullable = false)
    private String category;

    @Size(min = 1, max = 200)
    @Column(length = 200)
    private String tags;

    @NotNull
    @Column
    private long userId;

    @Size(min = 1, max = 500)
    @Column(length = 500)
    private String description;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private BookStatus status;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

    @Column
    private ZonedDateTime publishTime;

    @Column
    @LastModifiedDate
    private ZonedDateTime modifyTime;

    @OneToMany(mappedBy = "bookId", fetch = FetchType.EAGER, cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<BookChapter> bookChapters;

    public boolean isNew() {
        return id == null;
    }
}
