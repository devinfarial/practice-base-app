package com.mfarial.practicebaseapp.repositories;

import com.mfarial.practicebaseapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
