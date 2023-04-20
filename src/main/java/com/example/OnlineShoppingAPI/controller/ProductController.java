package com.example.OnlineShoppingAPI.controller;

import com.example.OnlineShoppingAPI.model.NewProduct;
import com.example.OnlineShoppingAPI.security.JwtAuthenticationFilter;
import com.example.OnlineShoppingAPI.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @GetMapping("allProduct")
    public ResponseEntity<Object> getAllProduct(@RequestParam(required = false,defaultValue = "0") int page,
                                                @RequestParam(required = false,defaultValue = "10") int limit){
        return ResponseEntity.ok().body(productServiceImpl.getAllProduct(page, limit));
    }

    @PostMapping("")
    public String addNewProduct(){

        return "Hello "+jwtAuthenticationFilter.user.getName();
    }

    @PostMapping("/addNewProduct")
    public ResponseEntity<Object> addProduct(@RequestBody NewProduct newProduct){
        return ResponseEntity.ok().body(productServiceImpl.addProduct(newProduct));
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable("productId") String productId,
                                @RequestParam(required = false)String productName,
                                @RequestParam(required = false)String productCost,
                                @RequestParam(required = false)String productType,
                                @RequestParam(required = false)String productCount){
        productServiceImpl.updateProduct(productId,productName,productCost,productType,productCount);
        return "Product updated successfully!!!";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") String productId){
        productServiceImpl.deleteProduct(productId);
        return ResponseEntity.ok().body("Product deleted successfully!!!");
    }
}
