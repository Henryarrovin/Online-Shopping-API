package com.example.OnlineShoppingAPI.service;

import com.example.OnlineShoppingAPI.dto.ProductDTO;
import com.example.OnlineShoppingAPI.model.NewProduct;
import com.example.OnlineShoppingAPI.model.Product;

import java.util.Objects;

public interface ProductService {
    Object getAllProduct(int page, int limit);
    Object addProduct(NewProduct newProduct);
    public Object updateProduct(Long productId, String productName, String productCost,  String productType, String productCount);
    public void deleteProduct(Long productId);
}
