package com.example.OnlineShoppingAPI.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long typeId;
    private String typeName;
}
