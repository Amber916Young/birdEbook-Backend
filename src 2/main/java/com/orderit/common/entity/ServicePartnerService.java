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
public class ServicePartnerService implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private Long servicePartnerId;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String serviceName;

    @Column(precision = 10, scale = 2)
    private BigDecimal servicePrice;

    @Column(precision = 10, scale = 2)
    private BigDecimal vat;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;
    public boolean isNew() {return id == null;}
}
