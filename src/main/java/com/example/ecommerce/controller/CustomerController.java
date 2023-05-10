package com.example.ecommerce.controller;

import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.RequestDto.UpdateCustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto2;
import com.example.ecommerce.DTO.ResponseDto.DeleteCustomerResponseDto;
import com.example.ecommerce.DTO.ResponseDto.UpdateCustomerResponseDto;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws Exception {

        return customerService.addCustomer(customerRequestDto);
    }
    // view all customers
    @GetMapping("/getAllCustomer")
    public List<CustomerResponseDto2> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    // get a customer by email
    @GetMapping("/getByEmail")
    public CustomerResponseDto2 getCustomerByEmail(@RequestParam("emailId")String emailId) throws Exception{
        return customerService.getCustomerByEmail(emailId);
    }

    // get all customers whose age is greater than 25
    @GetMapping("/getByAgeGreaterThan")
    public List<CustomerResponseDto2> getByAgeGreaterThan25(@RequestParam("age")int age){
        return customerService.getCustomerWithGreaterAge(age);
    }


    // get all customers who use VISA card
    @GetMapping("/getCustomersWhoUseVisaCard")
    public List<CustomerResponseDto2> getCustomersWithVisaCard(){
        return customerService.getCustomersWithVisaCard();
    }
    // update a customer info by email
    @PutMapping("/updateByEmail")
    public UpdateCustomerResponseDto updateByEmail(@RequestParam("email")String email, @RequestBody UpdateCustomerRequestDto
            updateCustomerRequestDto){
        return customerService.updateByEmail(email, updateCustomerRequestDto);
    }
    // delete a customer by email/mob
    @DeleteMapping("/deleteByMobile")
    //delete a customer by mobile
    public DeleteCustomerResponseDto deleteByMobile(@RequestParam("mobile")String email) throws Exception {
        return customerService.deleteByMobile(email);
    }
}
