package com.example.ecommerce.entity;

import com.example.ecommerce.enums.ProductCategory;
import com.example.ecommerce.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int price;

    int quantity;

    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    Item item;

    @ManyToOne
    @JoinColumn
    Seller seller;
}
