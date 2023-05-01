package com.example.ecommerce.service.impl;

import com.example.ecommerce.DAO.CustomerRepository;
import com.example.ecommerce.DAO.ItemRepository;
import com.example.ecommerce.DAO.ProductRepository;
import com.example.ecommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.enums.ProductStatus;
import com.example.ecommerce.exception.InvalidCustomerException;
import com.example.ecommerce.exception.InvalidProductException;
import com.example.ecommerce.service.ItemService;
import com.example.ecommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new InvalidProductException("Product doesn't exist");
        }

        if(itemRequestDto.getRequiredQuantity()>product.getQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("Product out of Stock");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItemList().add(item);
//        Product savedProduct = productRepository.save(product);

        // Think - only saving child will also work here ?????????

//        int size = product.getItemList().size();
        return itemRepository.save(item);
    }
}
