package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.data.RestResponse;
import org.example.data.dto.UserCreationDto;
import org.example.data.dto.UserTransferDto;
import org.example.service.TransferService;
import org.example.service.UserService;
import org.example.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;
    private final UserMapper userMapper;
    @Value("${security.auth.token}")
    private String authToken;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse create(@RequestHeader("Auth-Token") String token,
                               @RequestBody UserTransferDto userTransferDto) {
        if(token.equals(authToken)) {
            transferService.transfer(userTransferDto);
            return new RestResponse("Created");
        }else  return new RestResponse("Invalid token");
    }
}
