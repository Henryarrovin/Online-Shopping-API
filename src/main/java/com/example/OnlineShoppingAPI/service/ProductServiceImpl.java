package com.example.OnlineShoppingAPI.service;

import com.example.OnlineShoppingAPI.model.NewProduct;
import com.example.OnlineShoppingAPI.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo productRepo;
    @Value("${spring.productIdNotFound.error}")
    String productIdNotFound;
    @Override
    public NewProduct getAllProduct(int page, int limit) {
        Pageable pageable= PageRequest.of(page,limit);
        return (NewProduct) productRepo.findAll(pageable);
    }

    @Override
    public Object addProduct(NewProduct newProduct) {
        return productRepo.save(newProduct);
    }

    @Override
    @Transactional
    public void updateProduct(Long productId, String productName, String productCost, String productType, String productCount) {
        NewProduct newProduct=productRepo.findById(productId).orElseThrow(()->new IllegalStateException(productIdNotFound));

        if (productName!=null && productName.length()>0 && !Objects.equals(newProduct.getProductName(),productName)){
            newProduct.setProductName(productName);
        }
        if (productCost!=null && productCost.length()>0 && !Objects.equals(newProduct.getProductCost(),productCost)){
            newProduct.setProductCost(productCost);
        }
        if (productType!=null && productType.length()>0 && !Objects.equals(newProduct.getProductType(),productType)){
            newProduct.setProductType(productType);
        }
        if (productCount!=null && productCount.length()>0 && !Objects.equals(newProduct.getProductCount(),productCount)){
            newProduct.setProductCount(productCount);
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepo.deleteById(productId);
    }


}
