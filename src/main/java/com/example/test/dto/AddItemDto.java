package com.example.test.dto;

import com.example.test.model.OrderCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemDto {
    private Long idProduct;
    private int quantity;


}
