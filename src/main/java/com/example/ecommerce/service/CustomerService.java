package com.example.ecommerce.service;

import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.RequestDto.UpdateCustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto2;
import com.example.ecommerce.DTO.ResponseDto.DeleteCustomerResponseDto;
import com.example.ecommerce.DTO.ResponseDto.UpdateCustomerResponseDto;

import java.util.List;

public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws Exception;

    public List<CustomerResponseDto2> getAllCustomers();

    public CustomerResponseDto2 getCustomerByEmail(String emailId) throws Exception;

    List<CustomerResponseDto2> getCustomersWithVisaCard();

    List<CustomerResponseDto2> getCustomerWithGreaterAge(int age);

    DeleteCustomerResponseDto deleteByMobile(String mobile) throws Exception;

    UpdateCustomerResponseDto updateByEmail(String email, UpdateCustomerRequestDto updateCustomerRequestDto);
}
