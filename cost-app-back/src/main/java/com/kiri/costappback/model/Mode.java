package com.kiri.costappback.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mode {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotEmpty(message = "Mode name cannot be empty or null")
    @Column(unique = true)
    private String name;
}