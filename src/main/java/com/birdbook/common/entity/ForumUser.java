package com.birdbook.common.entity;

import com.birdbook.common.enums.Role;
import com.birdbook.common.enums.UserStatus;
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
public class ForumUser implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String username;

    @Size(min = 1, max = 200)
    @Column(length = 200)
    private String avatar_url;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(length = 150, nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private UserStatus status;

    @NotNull
    @Column(length = 200, nullable = false)
    private String password;


    public boolean isNew() {
        return id == null;
    }
}
