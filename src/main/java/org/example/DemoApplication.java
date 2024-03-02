package org.example;

import org.example.service.BankAccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
         ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
         BankAccountService bean = context.getBean(BankAccountService.class);
         bean.interestAccrual();

    }
}