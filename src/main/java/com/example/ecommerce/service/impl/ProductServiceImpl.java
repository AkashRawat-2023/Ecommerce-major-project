package com.example.ecommerce.service.impl;

import com.example.ecommerce.DAO.ProductRepository;
import com.example.ecommerce.DAO.SellerRepository;
import com.example.ecommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ecommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.enums.ProductCategory;
import com.example.ecommerce.exception.InvalidSellerException;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller =  sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new InvalidSellerException("Seller doesn't exist");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        // add product to current products of seller
        seller.getProducts().add(product);
        sellerRepository.save(seller);  // saves both seller and product

        // prepare Response Dto
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category) {

        List<Product> products = productRepository.findByProductCategory(category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }
}
