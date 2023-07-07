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
public class ServicePartner implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    private String servicePartnerName;

    @Column
    private int yearsOfExperience;

    @NotNull
    @Size(min = 1, max = 300)
    @Column(length = 300)
    private String description;

    @Column
    private String servicePartnerLogoUrl;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String servicePartnerEmail;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50)
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
    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String category;

    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String contactEmail;
    @Column(length = 100)
    private String companyWebsite;
    @Column
    @CreationTimestamp
    private ZonedDateTime createTime;

    @OneToMany(mappedBy = "servicePartnerId", cascade = ALL , fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ServicePartnerService> servicePartnerServices;


    public boolean isNew() {
        return id == null;
    }
}
