package com.example.ecommerce.DTO.ResponseDto;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String orderNo;

    int totalValue;

    Date orderDate;

    String cardUsed;

    List<ItemResponseDto> itemList;

    String customerName;


}
