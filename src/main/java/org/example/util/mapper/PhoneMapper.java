package org.example.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.data.dto.EmailUpdateDto;
import org.example.data.dto.PhoneDto;
import org.example.data.dto.PhoneUpdateDto;
import org.example.data.entity.Email;
import org.example.data.entity.PhoneNumber;
import org.example.data.entity.User;
import org.example.exceptions.NotFoundException;
import org.example.repository.PhoneRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneMapper {
    private final PhoneRepository phoneRepository;
    public PhoneNumber toEntityForCreation (String id, PhoneDto phoneCreationDto){
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(phoneCreationDto.getNumber());
        phoneNumber.setUser(new User(id));
        return phoneNumber;
    }

    public PhoneNumber toEntityForDelete(PhoneDto phoneDto){
        PhoneNumber phoneNumber = phoneRepository.findByNumber(phoneDto.getNumber()).orElseThrow(() -> new NotFoundException("Phone is not found"));
        return phoneNumber;
    }

    public PhoneNumber toEntityForUpdate(PhoneUpdateDto phoneUpdateDto){
        PhoneNumber number = phoneRepository.findByNumber(phoneUpdateDto.getOldNumber()).orElseThrow(() -> new NotFoundException("email is not found"));
        number.setNumber(phoneUpdateDto.getNewNumber());
        return number;
    }
}
