package com.example.ecommerce.service.impl;

import com.example.ecommerce.DAO.SellerRepository;
import com.example.ecommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.exception.EmailAlreadyPresentException;
import com.example.ecommerce.service.SellerService;
import com.example.ecommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {

//        CREATING RESPONSE DTO OBJECT
//        Seller seller = new Seller();
//        seller.setName(sellerRequestDto.getName());
//        seller.setEmailId(sellerRequestDto.getEmailId());
//        seller.setMobNo(sellerRequestDto.getMobNo());
//        seller.setAge(sellerRequestDto.getAge());

        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId()) != null)
            throw new EmailAlreadyPresentException("Email Id Already Registered");

//        CREATE DTO BY BUILDER. MORE EFFICIENT WAY
//        Converting DTO to Entity
        Seller seller = SellerTransformer.SellerRequestDtoToEntity(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);

//        prepare response dto
        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;
    }
}
