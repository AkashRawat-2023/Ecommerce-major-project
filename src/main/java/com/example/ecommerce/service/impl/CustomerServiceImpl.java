package com.example.ecommerce.service.impl;

import com.example.ecommerce.DAO.CardRepository;
import com.example.ecommerce.DAO.CustomerRepository;
import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.RequestDto.UpdateCustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto2;
import com.example.ecommerce.DTO.ResponseDto.DeleteCustomerResponseDto;
import com.example.ecommerce.DTO.ResponseDto.UpdateCustomerResponseDto;
import com.example.ecommerce.entity.Card;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.enums.CardType;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

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

    @Override
    public List<CustomerResponseDto2> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto2> ans = new ArrayList<>();
        for(Customer customer : customers ){
            CustomerResponseDto2 customerResponseDto = CustomerTransformer.customerToCustomerResponseDTO2(customer);
            ans.add(customerResponseDto);
        }
        return ans;
    }

    @Override
    public CustomerResponseDto2 getCustomerByEmail(String emailId) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findByEmailId(emailId);
        }catch (Exception e){
            throw  new Exception(("Customer with emailId does not exist"));
        }
        return CustomerTransformer.customerToCustomerResponseDTO2(customer);
    }

    @Override
    public List<CustomerResponseDto2> getCustomersWithVisaCard() {
        List<Card> cardList = cardRepository.findAll();
        List<Customer> customers = new ArrayList<>();
        for(Card card : cardList){
            if( card.getCardType() == CardType.VISA ) customers.add(card.getCustomer());
        }
        List<CustomerResponseDto2> customerResponseDtos = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponseDto2 customerResponseDto = CustomerTransformer.customerToCustomerResponseDTO2(customer);
            customerResponseDtos.add(customerResponseDto);
        }
        return customerResponseDtos;
    }

    @Override
    public List<CustomerResponseDto2> getCustomerWithGreaterAge(int age) {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponseDto2> ans = new ArrayList<>();
        for(Customer customer : customers){
            if(customer.getAge() > age) {
                CustomerResponseDto2 customerResponseDTO2 = CustomerTransformer.customerToCustomerResponseDTO2(customer);
                ans.add(customerResponseDTO2);
            }
        }
        return ans;
    }

    @Override
    public DeleteCustomerResponseDto deleteByMobile(String mobile) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findByMobNo(mobile);
            customerRepository.delete(customer);
        }
        catch(Exception e){
            throw new Exception("Mobile Number is not present");
        }
        return CustomerTransformer.customerToDeleteCustomerResponseDto(customer);
    }

    @Override
    public UpdateCustomerResponseDto updateByEmail(String email, UpdateCustomerRequestDto updateCustomerRequestDto) {
        Customer customer = customerRepository.findByEmailId(email);

        customer.setAge(updateCustomerRequestDto.getAge());
        customer.setMobNo(updateCustomerRequestDto.getMobile());
        Customer updated =  customerRepository.save(customer);
        UpdateCustomerResponseDto updateCustomerResponseDto =  CustomerTransformer.customerToResponse(updated);
        return updateCustomerResponseDto;
    }
}
