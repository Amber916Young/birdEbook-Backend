package com.orderit.common.entity;

import com.orderit.common.enums.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class SupplierOrder implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private Long companyId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus status;

    @Column
    private BigDecimal totalPrice;

    @Column
    private BigDecimal totalVat;

    @NotNull
    @Column
    private Long supplierId;

    @NotNull
    @Column
    private String supplierName;

    @Column
    private String supplierLogoUrl;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime ;

    @Column
    private LocalDate deliveryDate;

    @NotNull
    @Column
    private String createdBy;

    @OneToMany(mappedBy = "supplierOrder", fetch = FetchType.EAGER, cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<SupplierOrderItem> supplierOrderItems;

    public boolean isNew() {
        return id == null;
    }
}
