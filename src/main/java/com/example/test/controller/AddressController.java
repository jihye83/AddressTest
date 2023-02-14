package com.example.test.controller;

import com.example.test.model.Address;
import com.example.test.service.ViacepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private ViacepService viacepService;

    @GetMapping("/{cep}")
    public ResponseEntity<Address> getCep(@PathVariable String cep){
        Address address = viacepService.getCep(cep);

        return  address != null? ResponseEntity.ok().body(address): ResponseEntity.notFound().build();
    }
}
