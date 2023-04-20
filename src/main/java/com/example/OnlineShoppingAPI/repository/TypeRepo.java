package com.example.OnlineShoppingAPI.repository;

import com.example.OnlineShoppingAPI.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@Service
@EnableJpaRepositories
public interface TypeRepo extends JpaRepository<Type,Long> {
    Object findByTypeName(String type);
}
