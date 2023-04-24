package com.example.ecommerce.entity;

import com.example.ecommerce.enums.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cvv;

    @Column(unique = true,nullable = false)
    String cardNo;

    Date expiryDate;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;

}
