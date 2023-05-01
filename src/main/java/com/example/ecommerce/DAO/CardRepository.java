package com.example.ecommerce.DAO;

import com.example.ecommerce.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

        Card findByCardNo(String cardNo);
}
