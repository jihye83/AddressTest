package com.example.test.service;

import com.example.test.dto.AddItemDto;
import com.example.test.model.Item;
import com.example.test.model.OrderCart;
import com.example.test.model.Product;
import com.example.test.repository.ItemRepository;
import com.example.test.repository.OrderCartRepository;
import com.example.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderCartService {
    @Autowired
    private OrderCartRepository orderCartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderCart addItem(AddItemDto addItemDto) {
        //crie e salve um carrinho no meu DB
        OrderCart orderCart = new OrderCart();
        OrderCart saveOrderCart = orderCartRepository.save(orderCart);
        //findById(addItemDto.getIdProduct()).isPresent()
        //verificando se exist o produto e criando o Item.
        if (productRepository.existsById(addItemDto.getIdProduct())) {
            Product product = productRepository.findById(addItemDto.getIdProduct()).get();
            Item item = new Item();
            item.setProduct(product);
            item.setQuantity(addItemDto.getQuantity());
            item.setOrderCart(orderCart);
            orderCart.setItem(List.of(item));

            if (item.getQuantity() != 0) {
                double price = item.getQuantity() * product.getPrice();
                item.setSubPrice(price);
                itemRepository.save(item);
            }
            return orderCart;

        } else {
//                delete(item.getId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}

//    public OrderCart alterItem(AddItemDto addItemDto, Long id) {
//        itemRepository.findById(id).isPresent();
//        Item item = itemRepository.findById(id).get();
//        Product product = productRepository.findById(id).get();
//        item.setProduct(product);
//        item.setQuantity(addItemDto.getQuantity());
//        OrderCart orderCart = item.getOrderCart();
//
//        if (item.getQuantity() != 0) {
//            double price = item.getQuantity() * product.getPrice();
//            item.setSubPrice(price);
//        } else {
//
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//
//        return orderCart;
//    }



