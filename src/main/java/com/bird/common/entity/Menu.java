package com.bird.common.entity;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Menu {
    private Long id;
    private Long pid;
    private String name;
    private String link;
    private String icon;
    private List<Menu> children;
}
