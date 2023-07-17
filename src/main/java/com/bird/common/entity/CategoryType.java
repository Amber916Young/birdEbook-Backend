package com.bird.common.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * @ClassName:CategoryType
 * @Auther: yyj
 * @Description:
 * @Date: 10/07/2023 14:38
 * @Version: v1.0
 */

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CategoryType implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long pid;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    private String name;

    @Column
    private String icon;

    @Column
    private String description;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

    @OneToMany(mappedBy = "cateId", cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CategoryTypeUseLog> categoryTypeUseLogList;
    @Override
    public boolean isNew() {
        return id == null;
    }
}
