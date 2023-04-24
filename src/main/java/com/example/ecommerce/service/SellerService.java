package com.example.ecommerce.service;

import com.example.ecommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ecommerce.exception.EmailAlreadyPresentException;
import org.springframework.stereotype.Service;

public interface SellerService {

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException;
}
