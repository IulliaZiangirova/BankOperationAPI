package org.example.data.dto;

import lombok.Data;

@Data
public class UserTransferDto {
    private String fromUser;
    private String toUser;
    private Double amount;
}
