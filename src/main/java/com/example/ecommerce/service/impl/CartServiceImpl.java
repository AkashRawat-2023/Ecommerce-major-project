package com.example.ecommerce.service.impl;

import com.example.ecommerce.DAO.CardRepository;
import com.example.ecommerce.DAO.CartRepository;
import com.example.ecommerce.DAO.CustomerRepository;
import com.example.ecommerce.DAO.OrderedRepository;
import com.example.ecommerce.DTO.RequestDto.CheckoutCartRequestDto;
import com.example.ecommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ecommerce.DTO.ResponseDto.ItemResponseDto;
import com.example.ecommerce.DTO.ResponseDto.OrderResponseDto;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exception.InvalidCardException;
import com.example.ecommerce.exception.InvalidCustomerException;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderedRepository orderedRepository;

    @Override
    public CartResponseDto saveCart(Integer customerId, Item item) {

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getCartTotal() + item.getRequiredQuantity()*item.getProduct().getPrice();
        cart.setCartTotal(newTotal);
        cart.getItems().add(item);

        cart.setNumberOfItems(cart.getItems().size());
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(customer.getName())
                .numberOfItems(cart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item itemEntity: cart.getItems()){

            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(itemEntity.getProduct().getPrice());
            itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity()*itemEntity.getProduct().getPrice());
            itemResponseDto.setProductName(itemEntity.getProduct().getName());
            itemResponseDto.setQuantity(itemEntity.getRequiredQuantity());

            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }

    @Override
    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer id is invalid!!!");
        }

        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if(card==null || card.getCvv()!=checkoutCartRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0){
            throw new Exception("Cart is empty!!");
        }

        try{
            Ordered order = orderService.placeOrder(customer,card);  // throw exception if product goes out of stock
            customer.getOrderedList().add(order);
            Ordered savedOrder = orderedRepository.save(order);
            resetCart(cart);
//           customerRepository.save(customer);

            //prepare response dto
            // prepare response dto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item itemEntity: savedOrder.getItemList()){
                ItemResponseDto itemResponseDto = new ItemResponseDto();
                itemResponseDto.setPriceOfOneItem(itemEntity.getProduct().getPrice());
                itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity()*itemEntity.getProduct().getPrice());
                itemResponseDto.setProductName(itemEntity.getProduct().getName());
                itemResponseDto.setQuantity(itemEntity.getRequiredQuantity());

                items.add(itemResponseDto);
            }

            orderResponseDto.setItemList(items);
            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void resetCart(Cart cart){

        cart.setCartTotal(0);
        cart.setNumberOfItems(0);
        cart.setItems(new ArrayList<>());
    }
}
