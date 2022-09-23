package com.mfarial.practicebaseapp.services;

import com.mfarial.practicebaseapp.dto.request.CreateProductRequest;
import com.mfarial.practicebaseapp.dto.request.UpdateProductRequest;
import com.mfarial.practicebaseapp.entities.Product;
import com.mfarial.practicebaseapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void create(CreateProductRequest request){
        Product product = new Product();
        product.setFoodName(request.getFoodName());
        product.setQuantity(request.getQuantity());
        productRepository.save(product);
    }

    public void update(Long id, UpdateProductRequest request){
        Product product = productRepository.findById(id).orElseThrow();
        product.setFoodName(request.getFoodName());
        product.setQuantity(request.getQuantity());
        productRepository.save(product);
    }

    public void delete(Long id){
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }
}
