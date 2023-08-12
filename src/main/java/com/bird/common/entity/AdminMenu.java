package com.bird.common.entity;

import lombok.*;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class AdminMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long pid;
    @Column
    private String name;
    @Column
    private String link;
    @Column
    private String icon;
}
