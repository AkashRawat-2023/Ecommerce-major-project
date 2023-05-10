package com.example.ecommerce.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteCustomerResponseDto {

    String name;
    String mobile;
    String message;
}
