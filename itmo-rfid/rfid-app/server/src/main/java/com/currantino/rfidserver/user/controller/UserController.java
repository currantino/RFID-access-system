package com.currantino.rfidserver.user.controller;

import com.currantino.rfidserver.user.dto.CreateUserDto;
import com.currantino.rfidserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from rfid server!";
    }

    @GetMapping("/{id}")
    public UserDto getUserById(
            @RequestParam
            Long id
    ) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDto createUser(
            @Valid
            @RequestBody
            CreateUserDto createUserDto
    ) {
        return userService.createUser(createUserDto);
    }


}
