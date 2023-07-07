package com.orderit.common.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
public class Supplier implements Persistable<Long>  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String supplierName;

    @NotNull
    @Size(min = 1, max = 500)
    @Column(length = 500, nullable = false)
    private String description;

    @Column
    private String supplierLogoUrl;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String supplierEmail;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String contactNumber;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String addressLine3;

    @Column
    private String deliveryDay;

    @Column
    private String minOrder;
    @Column
    private String category;

    @Column
    private Boolean isIntegratedSupplier;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    private String cutOff;

    @Column
    private Boolean published;

    @CreationTimestamp
    @Column
    private ZonedDateTime createTime;

    @OneToMany(mappedBy = "supplierId", cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<SupplierProduct> supplierProducts;

    @Column
    private String deliveryDays;
    @Column
    private int cutOffHour;
    @Column
    private int cutOffMinute;
    @Column
    private int orderLeadDays;

    public boolean isNew() {
        return id == null;
    }
}
