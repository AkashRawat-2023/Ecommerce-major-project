package com.example.ecommerce.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {

    Integer cartTotal;

    Integer numberOfItems;

    String customerName;

    List<ItemResponseDto> items;
}
