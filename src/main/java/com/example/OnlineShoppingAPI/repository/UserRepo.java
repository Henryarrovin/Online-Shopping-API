package com.example.OnlineShoppingAPI.repository;

import com.example.OnlineShoppingAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    User getUserByUsername(String username);

}
