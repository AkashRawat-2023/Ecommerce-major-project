package com.example.ecommerce.transformer;

import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Customer;
import lombok.experimental.UtilityClass;

import java.util.Calendar;

@UtilityClass
public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

       return Customer.builder()
                .age(customerRequestDto.getAge())
                .name(customerRequestDto.getName())
                .address(customerRequestDto.getAddress())
                .emailId(customerRequestDto.getEmailId())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome " + customer.getName()+" to Amaozon !!")
                .build();
    }
}
