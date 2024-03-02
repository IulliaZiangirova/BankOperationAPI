package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.data.dto.UserCreationDto;
import org.example.data.RestResponse;
import org.example.service.CreateUserService;
import org.example.util.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/create")
@RequiredArgsConstructor
public class UserController {
    private final CreateUserService createUserService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse create(@RequestBody UserCreationDto userCreationDto) {
        createUserService.save(userMapper.toEntityForCreation(userCreationDto));
        return new RestResponse("Created");
    }
}
