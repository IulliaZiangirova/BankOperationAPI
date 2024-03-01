package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.data.RestResponse;
import org.example.service.FindUserService;
import org.example.util.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class FindController {
    private final FindUserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{number}")
    public RestResponse findByPhoneNumber(@PathVariable String number) {
        return new RestResponse(userMapper.toDto(userService.findByPhoneNumber(number)));
    }



}
