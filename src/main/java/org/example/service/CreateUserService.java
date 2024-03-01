package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.data.entity.User;
import org.example.repository.EmailRepository;
import org.example.repository.PhoneRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final PhoneRepository phoneRepository;

    public void save(User user){
        if (userRepository.findByLogin(user.getLogin()) != null){
            throw new RuntimeException("Login already exists");
        }
        if (user.getBankAccount().getBank() <= 0){
            throw new RuntimeException("Amount must be more than 0");
        }
        if (emailRepository.findByEmail(user.getEmailList().get(0).getEmail()) != null){
            throw new RuntimeException("Email is not unique");
        }
        if (phoneRepository.findByNumber(user.getPhoneNumberList().get(0).getNumber()) != null){
            throw new RuntimeException("Phone is not unique");
        }
        userRepository.save(user);
    }


}
