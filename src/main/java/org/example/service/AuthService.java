package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.data.entity.User;
import org.example.exceptions.AuthException;
import org.example.exceptions.NotFoundException;
import org.example.server.JwtRequest;
import org.example.server.JwtResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public JwtResponse login(JwtRequest authRequest) {
        User user = userService.findByLogin(authRequest.getLogin());
        if (user.getPassword().equals(authRequest.getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user);
            log.info(authRequest.getLogin() + " got token");
            return new JwtResponse(accessToken);
        } else {
            log.warn(authRequest.getLogin() + " entered wrong password");
            throw new AuthException("Wrong password");
        }
    }







}
