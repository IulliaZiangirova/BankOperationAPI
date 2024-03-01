package org.example.util.mapper;

import org.example.data.dto.UserCreationDto;
import org.example.data.entity.BankAccount;
import org.example.data.entity.Email;
import org.example.data.entity.PhoneNumber;
import org.example.data.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public User toEntityForCreation (UserCreationDto userCreationDto){
        User user = new User();
        user.setLogin(user.getLogin());
        user.setPassword(user.getPassword());
        user.setBankAccount(new BankAccount(userCreationDto.getStartAmount()));
        user.setEmailList(List.of(new Email(userCreationDto.getEmail())));
        user.setPhoneNumberList(List.of(new PhoneNumber(userCreationDto.getPhoneNumber())));
        return user;
    }
}
