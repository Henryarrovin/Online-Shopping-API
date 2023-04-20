package com.example.OnlineShoppingAPI.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productId;
    private String productName;
    private String productCost;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Type> types=new ArrayList<>();
}
