package com.mfarial.practicebaseapp.controllers;

import com.mfarial.practicebaseapp.dto.request.CreateProductRequest;
import com.mfarial.practicebaseapp.dto.request.UpdateProductRequest;
import com.mfarial.practicebaseapp.repositories.ProductRepository;
import com.mfarial.practicebaseapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<?>> getAllProduct(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(productRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request){
        productService.create(request);
        return ResponseEntity.ok("created");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request){
        productService.update(id, request);
        return ResponseEntity.ok("updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id){
        return ResponseEntity.ok("");
    }
}
