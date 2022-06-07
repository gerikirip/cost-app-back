package com.kiri.costappback.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cost {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotEmpty(message = "Cost name cannot be empty or null")
    private String name;
    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount cannot be negative or zero")
    private Long amount;
    private Long totalAmount;
    private Integer quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    @ManyToOne
    private Mode mode;

    @ManyToOne
    private Category category;
}
