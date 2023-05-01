package com.example.ecommerce.service;

import com.example.ecommerce.DTO.RequestDto.OrderRequestDto;
import com.example.ecommerce.DTO.ResponseDto.OrderResponseDto;
import com.example.ecommerce.entity.Card;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Ordered;

public interface OrderService {


    public Ordered placeOrder(Customer customer, Card card) throws Exception;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;
}
