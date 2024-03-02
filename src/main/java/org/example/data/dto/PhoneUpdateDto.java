package org.example.data.dto;

import lombok.Data;

@Data
public class PhoneUpdateDto {
    private String oldNumber;
    private String newNumber;
}
