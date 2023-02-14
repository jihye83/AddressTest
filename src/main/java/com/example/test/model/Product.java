package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name= "tb_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "This name attribute is mandatory!")
    @Size(min = 2, max = 100, message = "Minimum 05 and maximum 100 characters!")
    private String nameProd;
    private String description;
    private String size;
    private String image;
    @NotNull(message = "This price attribute is mandatory!")
    private Double price;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @OneToMany (mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("product")
    private List<Item> item;

//    @ManyToOne
//    @JsonIgnoreProperties("item")
//    private Item item;

}
