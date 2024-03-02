package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.data.dto.*;
import org.example.data.RestResponse;
import org.example.service.UserService;
import org.example.util.mapper.EmailMapper;
import org.example.util.mapper.PhoneMapper;
import org.example.util.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PhoneMapper phoneMapper;
    private final EmailMapper emailMapper;

//    @GetMapping("/search")
//    public RestResponse get (@RequestParam(value = "filter") String filter,
//                             @RequestParam(value = "value", defaultValue = "1") String value,
//                             @RequestParam(value = "page", defaultValue = "1") int page,
//                             @RequestParam (value = "limit", defaultValue = "10") int limit){
//        if(filter.equals("birthdate")){
//            userService.find
//        } else if(filter.equals("phone")){
//            userService.findUserByNumber(value);
//        } else if(filter.equals("login")){
//            userService.findUserByLogin(value);
//        } else if(filter.equals("email")){
//            userService.findUserByEmail(value)''
//        }
//        else
//        return new RestResponse(userService.get((page - 1) * limit,limit));
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse create(@RequestBody UserCreationDto userCreationDto) {
        userService.save(userMapper.toEntityForCreation(userCreationDto));
        return new RestResponse("Created");
    }

    @PostMapping("/add/phone/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse addPhone(@PathVariable String id, @RequestBody PhoneDto phoneCreationDto) {
        userService.addPhoneNumber(phoneMapper.toEntityForCreation(id, phoneCreationDto));
        return new RestResponse("Added");
    }

    @PostMapping("/add/email/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse addEmail(@PathVariable String id, @RequestBody EmailDto emailDto) {
        userService.addEmail(emailMapper.toEntityForCreation(id, emailDto));
        return new RestResponse("Added");
    }

    @DeleteMapping("/delete/email/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse deleteEmail(@PathVariable String id, @RequestBody EmailDto emailDto) {
        userService.deleteEmail(emailMapper.toEntityForDelete(emailDto));
        return new RestResponse("Deleted");
    }

    @PostMapping("/delete/phone/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse deletePhone(@PathVariable String id, @RequestBody PhoneDto phoneDto) {
        userService.deletePhoneNumber(phoneMapper.toEntityForDelete(phoneDto));
        return new RestResponse("Deleted");
    }

    @PatchMapping("/update/email/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse updateEmail(@PathVariable String id, @RequestBody EmailUpdateDto emailUpdateDto) {
        userService.updateEmail(emailMapper.toEntityForUpdate(emailUpdateDto));
        return new RestResponse("Updated");
    }

    @PatchMapping("/update/phone/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse updatePhone(@PathVariable String id, @RequestBody PhoneUpdateDto phoneUpdateDto) {
        userService.updatePhoneNumber(phoneMapper.toEntityForUpdate(phoneUpdateDto));
        return new RestResponse("Updated");
    }
}
