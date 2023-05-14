package com.example.OnlineShoppingAPI.controller;


import com.example.OnlineShoppingAPI.dto.AuthResponseDTO;
import com.example.OnlineShoppingAPI.dto.LoginDTO;
import com.example.OnlineShoppingAPI.dto.RegisterDTO;
import com.example.OnlineShoppingAPI.model.Role;
import com.example.OnlineShoppingAPI.model.User;
import com.example.OnlineShoppingAPI.repository.RoleRepo;
import com.example.OnlineShoppingAPI.repository.UserRepo;
import com.example.OnlineShoppingAPI.security.JwtGenerator;
import com.example.OnlineShoppingAPI.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    @Value("${spring.userNameTaken.error}")
    String usernameTaken;
    @Value("${spring.registered.success}")
    String registered;
    @Value("${spring.loggedIn.success}")
    String loggedIn;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;

    @GetMapping("/hello")
    public String hello(){
        return "page.html";
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        System.out.println("test 3");
        if (userRepo.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>(usernameTaken, HttpStatus.BAD_REQUEST);
        }

        User user =new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Role role=roleRepo.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(role));

        userRepo.save(user);

        return new ResponseEntity<>(registered,HttpStatus.OK);
    }

    @PostMapping("/register1")
    public String register1(@ModelAttribute RegisterDTO registerDTO){
        if (userRepo.existsByUsername(registerDTO.getUsername())){
            return "page.html";
        }

        User user =new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Role role=roleRepo.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(role));

        userRepo.save(user);

        return "page.html";
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){

        System.out.println("test 1");
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),loginDTO.getPassword()));
        System.out.println("test 2");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("test 3");
        String token= jwtGenerator.generateToken(authentication);
        System.out.println("test 4");
        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("userId")Long id){
       userService.deleteUserById(id);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable("id") Long id,
                           @RequestParam(required = false)String name,
                           @RequestParam(required = false)String userName){
        userService.updateStudent(id, name, userName);
    }
}
