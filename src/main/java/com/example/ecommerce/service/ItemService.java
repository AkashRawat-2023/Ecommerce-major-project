package com.example.ecommerce.service;

import com.example.ecommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.exception.InvalidCustomerException;

public interface ItemService {

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}
