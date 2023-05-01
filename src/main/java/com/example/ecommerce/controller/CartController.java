package com.example.ecommerce.controller;

import com.example.ecommerce.DTO.RequestDto.CheckoutCartRequestDto;
import com.example.ecommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ecommerce.DTO.ResponseDto.OrderResponseDto;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception{

        try{
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(),savedItem);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        return cartService.checkOutCart(checkoutCartRequestDto);
    }
}
