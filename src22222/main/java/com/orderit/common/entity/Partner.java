package com.orderit.common.entity;

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
public class Partner implements Persistable<Long>  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String partnerName;

    @NotNull
    @Size(min = 1, max = 500)
    @Column(length = 500, nullable = false)
    private String description;

    @Column
    private String partnerLogoUrl;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String partnerEmail;

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

    @CreationTimestamp
    @Column
    private ZonedDateTime createTime;

    @OneToMany(mappedBy = "partnerId", fetch = FetchType.EAGER, cascade = ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PartnerProduct> partnerProducts;

    public boolean isNew() {
        return id == null;
    }
}
