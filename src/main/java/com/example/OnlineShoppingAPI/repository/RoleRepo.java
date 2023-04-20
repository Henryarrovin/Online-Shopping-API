package com.example.OnlineShoppingAPI.repository;

import com.example.OnlineShoppingAPI.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableJpaRepositories
public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
