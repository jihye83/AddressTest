package com.example.test.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

}
