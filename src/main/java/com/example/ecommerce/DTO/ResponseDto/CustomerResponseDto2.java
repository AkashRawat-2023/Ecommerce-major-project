package com.example.ecommerce.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CustomerResponseDto2 {
    String name;
    String mobile ;
    int age;
    String email;
    String address;
}
