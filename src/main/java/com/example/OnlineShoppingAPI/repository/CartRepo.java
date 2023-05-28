package com.example.OnlineShoppingAPI.repository;

import com.example.OnlineShoppingAPI.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableJpaRepositories
public interface CartRepo extends JpaRepository<CartProduct,Long> {
    Optional<CartProduct> findById(Long productId);
}
