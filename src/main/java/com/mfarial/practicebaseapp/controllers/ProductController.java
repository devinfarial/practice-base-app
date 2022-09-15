package com.mfarial.practicebaseapp.controllers;

import com.mfarial.practicebaseapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<?>> getAllProduct(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(productRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(){
        return ResponseEntity.ok("");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id){
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id){
        return ResponseEntity.ok("");
    }
}
