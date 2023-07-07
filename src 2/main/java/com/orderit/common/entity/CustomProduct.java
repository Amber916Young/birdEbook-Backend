package com.orderit.common.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CustomProduct implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private Long companyId;

    @NotNull
    @Column
    private Long supplierId;
    @Column
    private String productCode;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String productName;

    @Column
    private String description;

    @Column
    private String productImageUrl;

    @Column(precision = 10, scale = 2)
    private BigDecimal vat;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column
    private String unit;

    @Column(length = 45)
    private String category;

    @CreationTimestamp
    @Column
    private ZonedDateTime createTime;

    public boolean isNew() {
        return id == null;
    }
}
