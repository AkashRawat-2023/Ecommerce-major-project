package com.example.ecommerce.service.impl;

import com.example.ecommerce.DAO.CustomerRepository;
import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws Exception {

        if(customerRepository.findByMobNo(customerRequestDto.getMobNo()) != null)
            throw new Exception("Customer already Present");

//        request dto --> customer entity
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = Cart.builder()
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();

        customer.setCart(cart);
       Customer savedCustomer =  customerRepository.save(customer); // customer and cart

//        prepare to response dto

        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}
