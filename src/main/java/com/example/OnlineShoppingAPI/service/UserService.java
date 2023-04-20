package com.example.OnlineShoppingAPI.service;


import com.example.OnlineShoppingAPI.model.Role;
import com.example.OnlineShoppingAPI.model.User;

import java.util.List;

public interface UserService{
    User saveUser(User user);
    Role saveRole(Role role);
    List<User>getUsers();
    public void deleteUserById(Long id);
    public User getUserByUserName(String username);
    public void updateStudent(Long id,String name,String username);
}
