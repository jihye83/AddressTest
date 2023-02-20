package com.example.test.controller;

import com.example.test.dto.AddItemDto;
import com.example.test.model.OrderCart;
import com.example.test.repository.OrderCartRepository;
import com.example.test.service.OrderCartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderCarts")
public class OrderCartController {
    @Autowired
    private OrderCartRepository orderRepository;
    @Autowired
    private OrderCartService orderCartService;

    @GetMapping
    public ResponseEntity<List<OrderCart>> getAll(){
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping({"/id"})
    public ResponseEntity<OrderCart> getById(@PathVariable Long id){
        return orderRepository.findById(id).map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<OrderCart> post(@Valid @RequestBody AddItemDto addItemDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderCartService.addItem(addItemDto));
    }

//    @PutMapping
//    public ResponseEntity<OrderCart> put(@PathVariable Long id, @Valid @RequestBody AddItemDto addItemDto){
//        return ResponseEntity.status(HttpStatus.CREATED).body(orderCartService.alterItem(addItemDto,id));
//    }

}
