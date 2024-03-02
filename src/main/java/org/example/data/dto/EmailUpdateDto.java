package org.example.data.dto;

import lombok.Data;

@Data
public class EmailUpdateDto {
    private String oldEmail;
    private String newEmail;
}
