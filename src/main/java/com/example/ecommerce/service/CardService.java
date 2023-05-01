package com.example.ecommerce.service;

import com.example.ecommerce.DTO.RequestDto.CardRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CardResponseDto;
import com.example.ecommerce.exception.InvalidCustomerException;

public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
}
