package com.example.ecommerce.transformer;

import com.example.ecommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ecommerce.entity.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerTransformer {

    // static --  Utility Class -- A class whose object can't be made
    public static Seller SellerRequestDtoToEntity(SellerRequestDto sellerRequestDto){

        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .age(sellerRequestDto.getAge())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .build();
    }
}
