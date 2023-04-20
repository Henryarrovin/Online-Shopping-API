package com.example.OnlineShoppingAPI.service;

import com.example.OnlineShoppingAPI.model.Role;
import com.example.OnlineShoppingAPI.model.User;
import com.example.OnlineShoppingAPI.repository.RoleRepo;
import com.example.OnlineShoppingAPI.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    @Value("${spring.userNameNotFound.error}")
    String userNameNotFound;
    @Value("${spring.userIdNotFound.error}")
    String userIdNotFound;
    @Value("${spring.userNotExist.error}")
    String userNotExist;
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        boolean exist = userRepo.existsById(id);
        if(!exist){
            throw new IllegalStateException(userIdNotFound);
        }
        userRepo.deleteById(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepo.getUserByUsername(username);
    }

    @Override
    @Transactional
    public void updateStudent(Long id, String name, String username) {
        User user=userRepo.findById(id).orElseThrow(()->new IllegalStateException(userNotExist));

        if (name!=null && name.length()>0 && !Objects.equals(user.getName(),name)){
            user.setName(name);
        }
        if (username!=null && username.length()>0 && !Objects.equals(user.getUsername(),username)){
            Optional<User> userOptional=userRepo.findByUsername(username);
            if (userOptional.isPresent()){
                throw new IllegalStateException("username taken");
            }
            user.setUsername(username);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(userNameNotFound));
        org.springframework.security.core.userdetails.User user1=new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesToAuthority((List<Role>) user.getRoles()));
        System.out.println(user);

        return user1;

    }

    private Collection<GrantedAuthority> mapRolesToAuthority(List<Role> roles){
        System.out.println(roles);
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
