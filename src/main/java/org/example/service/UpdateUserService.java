package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.EmailRepository;
import org.example.repository.PhoneRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService {
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final PhoneRepository phoneRepository;
}
