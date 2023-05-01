package com.example.ecommerce.service;

import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;

public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws Exception;
}
