package com.example.ecommerce.service;

import com.example.ecommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ecommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.enums.ProductCategory;
import com.example.ecommerce.exception.InvalidSellerException;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category);

    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category);

    public void decreaseProductQuantity(Item item) throws Exception;
}
