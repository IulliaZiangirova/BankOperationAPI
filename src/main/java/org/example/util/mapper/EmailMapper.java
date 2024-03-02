package org.example.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.data.dto.EmailDto;
import org.example.data.dto.EmailUpdateDto;
import org.example.data.entity.Email;
import org.example.data.entity.User;
import org.example.exceptions.NotFoundException;
import org.example.repository.EmailRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailMapper {
    private final EmailRepository emailRepository;

    public Email toEntityForCreation (String id, EmailDto emailCreationDto){
        Email email = new Email();
        email.setEmail(emailCreationDto.getEmail());
        email.setUser(new User(id));
        return email;
    }

    public Email toEntityForDelete(EmailDto emailCreationDto){
    Email email = emailRepository.findByEmail(emailCreationDto.getEmail()).orElseThrow(() -> new NotFoundException("email is not found"));
    return email;
    }

    public Email toEntityForUpdate(EmailUpdateDto emailUpdateDto){
        Email email = emailRepository.findByEmail(emailUpdateDto.getOldEmail()).orElseThrow(() -> new NotFoundException("email is not found"));
        email.setEmail(emailUpdateDto.getNewEmail());
        return email;
    }
}
