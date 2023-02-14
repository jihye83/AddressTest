package com.example.test.dto;

import com.example.test.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String nameProd;
    private String description;
    private String size;
    private String image;
    private Double price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.nameProd = product.getNameProd();
        this.description = product.getDescription();
        this.size = product.getSize();
        this.image = product.getImage();
        this.price = product.getPrice();
    }

    /* inserting Pageable */
    public static Page<ProductDto> convert(Page<Product> products){

        return products.map(ProductDto::new);
    }

//    public static List<ProductDto> convert(List<Product> products){
//        return products.stream().map(ProductDto::new).collect(Collectors.toList());
//    }

}
