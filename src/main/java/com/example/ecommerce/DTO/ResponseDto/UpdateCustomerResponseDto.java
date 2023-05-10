package com.example.ecommerce.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCustomerResponseDto {

    String mobile;
    int age;
    String message;
}
