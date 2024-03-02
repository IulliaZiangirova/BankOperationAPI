package org.example.service;

import lombok.AllArgsConstructor;
import org.example.data.dto.UserTransferDto;
import org.example.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransferService {
    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public void transfer (UserTransferDto userTransferDto){

    }


}
