package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.data.entity.Email;
import org.example.data.entity.PhoneNumber;
import org.example.data.entity.User;
import org.example.exceptions.NotAllowException;
import org.example.exceptions.NotFoundException;
import org.example.repository.EmailRepository;
import org.example.repository.PhoneRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final PhoneRepository phoneRepository;

    @Transactional
    public void save(User user){
        if (userRepository.findByLogin(user.getLogin()) != null){
            throw new NotAllowException("Login already exists");
        }
        if (user.getBankAccount().getAmount() <= 0){
            throw new NotAllowException("Amount must be more than 0");
        }
        if (emailRepository.findByEmail(user.getEmailList().get(0).getEmail()).isPresent()){
            throw new NotAllowException("Email is not unique");
        }
        if (phoneRepository.findByNumber(user.getPhoneNumberList().get(0).getNumber()).isPresent()){
            throw new NotAllowException("Phone is not unique");
        }
        userRepository.save(user);
    }

    @Transactional
    public void addPhoneNumber(PhoneNumber phoneNumber){
        User user = userRepository.findById(phoneNumber.getUser().getId()).orElseThrow(()-> new NotFoundException("User id not found"));
        if (phoneRepository.findByNumber(user.getPhoneNumberList().get(0).getNumber()).isPresent()){
            throw new NotAllowException("Phone is not unique");
        }
        phoneRepository.save(phoneNumber);
    }

    @Transactional
    public void addEmail(Email email){
        User user = userRepository.findById(email.getUser().getId()).orElseThrow(()-> new NotFoundException("User id not found"));
        if (emailRepository.findByEmail(user.getEmailList().get(0).getEmail()).isPresent()){
            throw new NotAllowException("Email is not unique");
        }
        emailRepository.save(email);
    }

    @Transactional
    public void deleteEmail(Email email){
        User user = userRepository.findById(email.getUser().getId()).orElseThrow(()-> new NotFoundException("User is not found"));
        if (user.getEmailList().size() == 1){
            throw new NotAllowException("You can't delete this email");
        }
        emailRepository.delete(email);
    }

    @Transactional
    public void deletePhoneNumber(PhoneNumber phoneNumber){
        User user = userRepository.findById(phoneNumber.getUser().getId()).orElseThrow(()-> new NotFoundException("User id not found"));
        if (user.getPhoneNumberList().size() == 1){
            throw new NotAllowException("You can't delete this number");
        }
        phoneRepository.delete(phoneNumber);
    }

    @Transactional
    public void updateEmail(Email email){
        if (emailRepository.findByEmail(email.getEmail()).isPresent()){
            throw new NotAllowException("Email is not unique");
        }
        emailRepository.save(email);
    }

    @Transactional
    public void updatePhoneNumber(PhoneNumber phoneNumber){
        if (phoneRepository.findByNumber(phoneNumber.getNumber()).isPresent()){
            throw new NotAllowException("Phone is not unique");
        }
        phoneRepository.save(phoneNumber);
    }




}
