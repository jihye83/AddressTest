package com.example.test.service;

import com.example.test.dto.AddItemDto;
import com.example.test.model.Item;
import com.example.test.model.OrderCart;
import com.example.test.model.Product;
import com.example.test.repository.ItemRepository;
import com.example.test.repository.OrderCartRepository;
import com.example.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCartService {
    @Autowired
    private OrderCartRepository orderCartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderCart addItem(AddItemDto addItemDto){
        //crie e salve um carrinho no meu DB
        OrderCart orderCart = new OrderCart();
        OrderCart saveOrderCart = orderCartRepository.save(orderCart);
        //findById(addItemDto.getIdProduct()).isPresent()
        //verificando se exist o produto e criando o Item.
        if(productRepository.existsById(addItemDto.getIdProduct())){
            Product product = productRepository.findById(addItemDto.getIdProduct()).get();
            Item item = new Item();
            item.setProduct(product);
            item.setQuantity(addItemDto.getQuantity());
            item.setOrderCart(orderCart);
            itemRepository.save(item);
        }
        return orderCart;
    }

}
