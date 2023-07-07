package com.orderit.common.entity;

import com.orderit.common.enums.Role;
import com.orderit.common.enums.UserStatus;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CompanyUser implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @NotNull
    @Column
    private Long companyId;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String email;
    
    @Size(min = 1, max = 100)
    @Column(length = 100)
    private String contactNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private UserStatus status;

    @NotNull
    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 20)
    private String resetKey;

    public boolean isNew() {
        return id == null;
    }
}
