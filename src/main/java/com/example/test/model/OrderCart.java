package com.example.test.model;

import com.example.test.model.enums.OrderCartStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "tb_orders")
public class OrderCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @NotNull
    private double TotalPrice;
    private OrderCartStatus status;
    @OneToMany(mappedBy = "orderCart", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("orderCart")
    private List<Item> item;
}
