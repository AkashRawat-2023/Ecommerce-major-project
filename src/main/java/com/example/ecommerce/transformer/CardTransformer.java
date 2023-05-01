package com.example.ecommerce.transformer;

import com.example.ecommerce.DTO.RequestDto.CardRequestDto;
import com.example.ecommerce.entity.Card;

public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }
}
