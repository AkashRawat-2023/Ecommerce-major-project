package com.example.ecommerce.transformer;

import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto2;
import com.example.ecommerce.DTO.ResponseDto.DeleteCustomerResponseDto;
import com.example.ecommerce.DTO.ResponseDto.UpdateCustomerResponseDto;
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

    public static CustomerResponseDto2 customerToCustomerResponseDTO2(Customer customer){
        return CustomerResponseDto2.builder()
                .name(customer.getName())
                .mobile(customer.getMobNo())
                .age(customer.getAge())
                .email(customer.getEmailId())
                .build();
    }
    public static DeleteCustomerResponseDto customerToDeleteCustomerResponseDto(Customer customer){
        return DeleteCustomerResponseDto.builder()
                .name(customer.getName())
                .mobile(customer.getMobNo())
                .message("Customer Deleted Successfully").build();
    }

    public static UpdateCustomerResponseDto customerToResponse(Customer customer){
        return UpdateCustomerResponseDto.builder()
                .age(customer.getAge())
                .mobile(customer.getMobNo())
                .message("Customer Details Updated").build();
    }


}
