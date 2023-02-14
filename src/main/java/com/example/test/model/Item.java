package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private double subPrice;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
//    @JsonIgnoreProperties("item")
//    private List<Product> product;

    @ManyToOne
    @JsonIgnoreProperties("item")
    private OrderCart orderCart;

    @ManyToOne
    @JsonIgnoreProperties("item")
    private Product product;

}
