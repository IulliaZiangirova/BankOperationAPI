package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.data.entity.BankAccount;
import org.example.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private Map<String, Double> startAmount;
    private List<BankAccount> accounts;
    private Timer timer = new Timer();


    private List<BankAccount> getAllBankAccount(){
        return bankAccountRepository.findAll();
    }
    public BankAccount getByUserId (String id){
       return bankAccountRepository.findByUserId(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(BankAccount bankAccount){
        bankAccountRepository.save(bankAccount);
    }
    @Transactional
    public void interestAccrual(){
        accounts = getAllBankAccount();
        startAmount = new HashMap<>();
        for ( BankAccount account : accounts) {
            startAmount.put(account.getAccountId(), account.getAmount());
        }
        timer.schedule(new MyTimerTask(),0, 60*1000);
    }

    class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            increaseAmount();
        }

        private void increaseAmount (){
            log.info("Increasing start");
            double scale = Math.pow(10,2);
            for (BankAccount account : accounts) {
                double newAmount = account.getAmount() * 1.05;
                newAmount = Math.ceil(newAmount * scale)/ scale;
                if(newAmount <= startAmount.get(account.getAccountId()) * 2.07){
                    account.setAmount(newAmount);
                    log.info(account.getAccountId() + " success increasing");
                    save(account);
                }else log.info(account.getAccountId() + " reach 207%");
            }
        }
    }
}
