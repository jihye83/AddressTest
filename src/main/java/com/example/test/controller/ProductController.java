package com.example.test.controller;

import com.example.test.dto.ProductDto;
import com.example.test.model.Product;
import com.example.test.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    /*
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }
    */

    /* Inserting ProductDto in getAll
    @GetMapping
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        return ProductDto.convert(products);
    }
    */

    /*
    *@GetMapping
    public Page<ProductDto> getAll(@RequestParam(required = false) String product,
                                   @RequestParam int page,
                                   @RequestParam int qtd) {
        Pageable pageable = PageRequest.of(page, qtd);
        Page<Product> products = productRepository.findAll(pageable);
        return ProductDto.convert(products);
    }
    */

    /* inserting Pageable with Sort*/
    @GetMapping
    public Page<ProductDto> getAll(@PageableDefault(page=0, size = 3, direction = Sort.Direction.ASC)
                                   Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return ProductDto.convert(products);
    }

//    @GetMapping("/{nameProd}")
//    public ResponseEntity<ProductDto> getByName(@PathVariable String nameProd) {
//        Page<Product> product = productRepository.findAll(Pageable.unpaged());
//        if(product.isPresent()){
//            return ResponseEntity.ok(new ProductDto((Product) product.get()));
//        }
//        return ResponseEntity.notFound().build();
//    }
    /* inserting Dto + Pageable + sorting name in ASC */
    @GetMapping("/name")
    public ResponseEntity<Page<ProductDto>> getByName(@RequestParam(name = "nameProd") String nameProd,
                                                   @PageableDefault(page = 0, size = 3, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(productRepository.getName(nameProd, pageable));
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productRepository.findById(id).map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return ResponseEntity.ok(new ProductDto(product.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> post(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    @PutMapping
    public ResponseEntity<Product> put(@Valid @RequestBody Product product) {
        if(productRepository.existsById(product.getId()))
            return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        productRepository.deleteById(id);
    }

}
