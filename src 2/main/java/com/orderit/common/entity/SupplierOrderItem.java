package com.orderit.common.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class SupplierOrderItem implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private Long supplierProductId;

    @NotNull
    @Column
    private String supplierProductName;

    @Column
    private String nickname;

    @NotNull
    @Column
    private BigDecimal quantity;

    @NotNull
    @Column
    private BigDecimal vat;

    @NotNull
    @Column
    private BigDecimal price;
    @Column
    private String unit;

    @CreationTimestamp
    @Column
    private ZonedDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "supplier_order_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SupplierOrder supplierOrder;

    public boolean isNew() {
        return id == null;
    }
}
