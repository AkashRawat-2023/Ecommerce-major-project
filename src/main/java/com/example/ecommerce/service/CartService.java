package com.example.ecommerce.service;

import com.example.ecommerce.DTO.RequestDto.CheckoutCartRequestDto;
import com.example.ecommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ecommerce.DTO.ResponseDto.OrderResponseDto;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.exception.InvalidCustomerException;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


public interface CartService {
    public CartResponseDto saveCart(Integer customerId, Item item);

    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception;

}
