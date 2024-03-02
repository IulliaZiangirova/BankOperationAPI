package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.data.RestResponse;
import org.example.data.dto.UserCreationDto;
import org.example.data.dto.UserTransferDto;
import org.example.service.TransferService;
import org.example.service.UserService;
import org.example.util.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;
    private final UserMapper userMapper;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse create(@RequestBody UserTransferDto userTransferDto) {
        transferService.transfer(userTransferDto);
        return new RestResponse("Created");
    }
}
