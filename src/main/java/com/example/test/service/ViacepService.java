package com.example.test.service;

import com.example.test.client.ViacepFeignClient;
import com.example.test.model.Address;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ViacepService {
    private ViacepFeignClient viacepFeignClient;

    @Autowired
    public ViacepService(ViacepFeignClient viacepFeignClient) {

        this.viacepFeignClient = viacepFeignClient;
    }

    public Address getCep(String cep) {
        try {
            Address address = viacepFeignClient.getCep(cep);
            return address;
        } catch (FeignException feignException){
            System.out.println("Erro ao pegar cep");
            return null;
        }

    }
}