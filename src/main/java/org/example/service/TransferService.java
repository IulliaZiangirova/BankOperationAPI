package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.data.dto.UserTransferDto;
import org.example.data.entity.BankAccount;
import org.example.data.entity.User;
import org.example.exceptions.NotAllowException;
import org.example.repository.BankAccountRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class TransferService {
    private final BankAccountService bankAccountService;
    private final UserService userService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transfer (UserTransferDto userTransferDto){
        User fromUser = userService.findByLogin(userTransferDto.getFromUser());
        User toUser = userService.findByLogin(userTransferDto.getToUser());
        BankAccount fromAccount = bankAccountService.getByUserId(fromUser.getId());
        BankAccount toAccount = bankAccountService.getByUserId(toUser.getId());
        if (fromAccount.getAmount() < userTransferDto.getAmount()){
            log.info(userTransferDto.getFromUser() + " doesn't have enough money");
            throw new NotAllowException("doesn't have enough money ");
        }else {
            fromAccount.setAmount(fromAccount.getAmount() - userTransferDto.getAmount());
            toAccount.setAmount(toAccount.getAmount() + userTransferDto.getAmount());
            log.info(userTransferDto.getFromUser() + " transfered " + fromAccount.getAmount() + " to " + userTransferDto.getToUser());
        }

    }


}
