package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.data.dto.*;
import org.example.data.RestResponse;
import org.example.data.entity.User;
import org.example.service.UserService;
import org.example.util.mapper.EmailMapper;
import org.example.util.mapper.PhoneMapper;
import org.example.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PhoneMapper phoneMapper;
    private final EmailMapper emailMapper;
    @Value("${security.auth.token}")
    private String authToken;

    @GetMapping("/search")
    public RestResponse get (@RequestHeader("Auth-Token") String token,
                            @RequestParam(value = "filter") String filter,
                             @RequestParam(value = "value") String value,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam (value = "limit", defaultValue = "10") int limit){
        if(token.equals(authToken)){
        if(filter.equals("birthdate")){
            return new RestResponse(userService.findByBirthdate(value));
        } else if(filter.equals("phone")){
            return new RestResponse(userService.findByNumber(value));
        } else if(filter.equals("login")){
            return new RestResponse(userService.findByLoginLike(value));
        } else if(filter.equals("email")){
            return new RestResponse(userService.findByEmail(value));
        }
        else return new RestResponse("Invalid filter");
    }else  return new RestResponse("Invalid token");}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse create(@RequestBody UserCreationDto userCreationDto) {
        userService.save(userMapper.toEntityForCreation(userCreationDto));
        return new RestResponse("Created");
    }

    @PostMapping("/add/phone/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse addPhone(@RequestHeader("Auth-Token") String token, @PathVariable String id, @RequestBody PhoneDto phoneCreationDto) {
        if(token.equals(authToken)){
        userService.addPhoneNumber(phoneMapper.toEntityForCreation(id, phoneCreationDto));
        return new RestResponse("Added");
    }else  return new RestResponse("Invalid token");}

    @PostMapping("/add/email/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse addEmail(@RequestHeader("Auth-Token") String token,@PathVariable String id, @RequestBody EmailDto emailDto) {
        if(token.equals(authToken)){
        userService.addEmail(emailMapper.toEntityForCreation(id, emailDto));
        return new RestResponse("Added");
    }else  return new RestResponse("Invalid token");}

    @DeleteMapping("/delete/email/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse deleteEmail(@RequestHeader("Auth-Token") String token, @PathVariable String id, @RequestBody EmailDto emailDto) {
        if(token.equals(authToken)){
        userService.deleteEmail(emailMapper.toEntityForDelete(emailDto));
        return new RestResponse("Deleted");
    }else  return new RestResponse("Invalid token");}

    @PostMapping("/delete/phone/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse deletePhone(@RequestHeader("Auth-Token") String token, @PathVariable String id, @RequestBody PhoneDto phoneDto) {
        if(token.equals(authToken)){
        userService.deletePhoneNumber(phoneMapper.toEntityForDelete(phoneDto));
        return new RestResponse("Deleted");
    }else  return new RestResponse("Invalid token");}

    @PatchMapping("/update/email/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse updateEmail(@RequestHeader("Auth-Token") String token, @PathVariable String id, @RequestBody EmailUpdateDto emailUpdateDto) {
        if(token.equals(authToken)){
        userService.updateEmail(emailMapper.toEntityForUpdate(emailUpdateDto));
        return new RestResponse("Updated");
    }else  return new RestResponse("Invalid token");}

    @PatchMapping("/update/phone/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse updatePhone(@RequestHeader("Auth-Token") String token, @PathVariable String id, @RequestBody PhoneUpdateDto phoneUpdateDto) {
        if(token.equals(authToken)){
        userService.updatePhoneNumber(phoneMapper.toEntityForUpdate(phoneUpdateDto));
        return new RestResponse("Updated");
    }else  return new RestResponse("Invalid token");}
}
