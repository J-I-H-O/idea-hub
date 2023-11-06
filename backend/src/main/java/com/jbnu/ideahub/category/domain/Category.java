package com.jbnu.ideahub.category.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;
}
