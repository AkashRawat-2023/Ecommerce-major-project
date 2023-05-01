package com.example.ecommerce.controller;

import com.example.ecommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ecommerce.exception.EmailAlreadyPresentException;
import com.example.ecommerce.service.SellerService;
import com.example.ecommerce.service.impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("seller")
public class SellerController {

    @Autowired
    SellerServiceImpl sellerServiceImpl;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {

        try {
            SellerResponseDto sellerResponseDto = sellerServiceImpl.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
    //  GET a seller by email

    // get by id

    // get all seller

    // update seller info based on email id

    // delete a seller based on email

    //delete by id

    // get all sellers of a particular age

