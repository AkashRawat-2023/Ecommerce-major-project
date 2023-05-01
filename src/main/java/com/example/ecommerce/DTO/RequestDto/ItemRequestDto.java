package com.example.ecommerce.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestDto {

    int customerId;

    int productId;

    int requiredQuantity;
}
