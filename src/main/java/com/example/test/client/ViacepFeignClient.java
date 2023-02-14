package com.example.test.client;

import com.example.test.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viacep", url = "https://viacep.com.br/ws")
public interface ViacepFeignClient {

    @GetMapping("/{cep}/json")
    Address getCep(@PathVariable(value = "cep")String cep);

}
