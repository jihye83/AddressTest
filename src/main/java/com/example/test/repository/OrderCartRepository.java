package com.example.test.repository;

import com.example.test.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {

}
