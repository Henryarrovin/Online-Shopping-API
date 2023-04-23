package com.example.OnlineShoppingAPI.repository;

import com.example.OnlineShoppingAPI.model.NewProduct;
import com.example.OnlineShoppingAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableJpaRepositories
public interface ProductRepo extends JpaRepository<NewProduct,Long> {
    Optional<NewProduct> findById(Long productId);
}
