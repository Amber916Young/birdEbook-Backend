package com.orderit.common.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class FavouriteSupplier implements Persistable<Long> {
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
    private String shopName;

    @Column
    private String accountNumber;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

    public boolean isNew() {
        return id == null;
    }
}
