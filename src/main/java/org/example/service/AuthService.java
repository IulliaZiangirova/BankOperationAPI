package org.example.service;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.example.data.entity.User;
import org.example.exceptions.NotFoundException;
import org.example.server.JwtRequest;
import org.example.server.JwtResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    public JwtResponse login(JwtRequest authRequest) {
        final User user = userService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        if (user.getPassword().equals(authRequest.getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user);

            return new JwtResponse(accessToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }







}
