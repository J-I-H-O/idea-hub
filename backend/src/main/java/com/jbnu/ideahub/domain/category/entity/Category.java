package com.jbnu.ideahub.domain.category.entity;

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
