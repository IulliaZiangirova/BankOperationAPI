package org.example.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.data.dto.UserCreationDto;
import org.example.data.entity.BankAccount;
import org.example.data.entity.Email;
import org.example.data.entity.PhoneNumber;
import org.example.data.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toEntityForCreation (UserCreationDto userCreationDto){
        User user = new User();
        user.setLogin(userCreationDto.getLogin());
        user.setPassword(userCreationDto.getPassword());
        BankAccount bankAccount = new BankAccount(user, userCreationDto.getStartAmount());
        user.setBankAccount(bankAccount);
        Email email = new Email(user,userCreationDto.getEmail());
        PhoneNumber phoneNumber = new PhoneNumber(user,userCreationDto.getPhoneNumber() ) ;
        user.setEmailList(List.of(email));
        user.setPhoneNumberList(List.of(phoneNumber));

        return user;
    }
}
