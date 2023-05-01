package com.example.ecommerce.service.impl;

import com.example.ecommerce.DAO.CustomerRepository;
import com.example.ecommerce.DTO.RequestDto.CardRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CardResponseDto;
import com.example.ecommerce.entity.Card;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.exception.InvalidCustomerException;
import com.example.ecommerce.service.CardService;
import com.example.ecommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {

        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());
        if(customer==null){
            throw new InvalidCustomerException("Sorry! The customer doesn't exists");
        }

        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCardList().add(card);
        customerRepository.save(customer);

        // response dto
        return CardResponseDto.builder()
                .customerName(customer.getName())
                .cardNo(card.getCardNo())
                .build();
    }
}
