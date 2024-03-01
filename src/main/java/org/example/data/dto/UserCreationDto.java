package org.example.data.dto;

import lombok.Data;
import org.example.data.entity.PhoneNumber;

@Data
public class UserCreationDto {

    private String login;
    private String password;
    private Double startAmount;
    private String phoneNumber;
    private String email;
}
