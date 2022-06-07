package com.kiri.costappback.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotEmpty(message = "Category name cannot be empty or null")
    @Column(unique = true)
    private String name;
    private Long countCost;
}
