package com.orderit.common.entity;

import com.orderit.common.enums.CompanyStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Company implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String companyName;

    @Column
    private String companyLogoUrl;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String companyEmail;

    @Size(min = 1, max = 100)
    @Column(length = 100)
    private String contactNumber;

    @Size(min = 1, max = 100)
    @Column(length = 100)
    private String contactName;


    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String addressLine3;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private CompanyStatus status;

    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

    @OneToMany(mappedBy = "companyId", cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<FavouriteProduct> favouriteProducts;

    @OneToMany(mappedBy = "companyId", cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude

    private Set<FavouriteSupplier> favouriteSuppliers;

    @OneToMany(mappedBy = "companyId", cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<SupplierOrder> supplierOrders;

    @OneToMany(mappedBy = "companyId", fetch = FetchType.EAGER, cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<CompanyUser> companyUsers;

    public boolean isNew() {
        return id == null;
    }
}
