package com.example.test.service;

import com.example.test.client.ViacepFeignClient;
import com.example.test.model.Address;
import org.springframework.stereotype.Service;

@Service
public class ViacepService {
    private ViacepFeignClient viacepFeignClient;

    public ViacepService(ViacepFeignClient viacepFeignClient){
        this.viacepFeignClient = viacepFeignClient;
    }

    public Address getCep (String cep){
        Address address = viacepFeignClient.getCep(cep);
        return  address;
    }
}