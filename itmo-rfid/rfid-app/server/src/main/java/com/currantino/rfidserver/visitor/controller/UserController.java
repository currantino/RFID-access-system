package com.currantino.rfidserver.visitor.controller;

import com.currantino.rfidserver.visitor.dto.CreateUserDto;
import com.currantino.rfidserver.visitor.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/visitors")
public class UserController {
    private final VisitorService visitorService;

    @GetMapping("/{id}")
    public UserDto getUserById(
            @RequestParam
            Long id
    ) {
        return visitorService.getUserById(id);
    }

    @GetMapping("/rfid/{rfidUid}")
    public UserDto getUserByCredentialId(
            @PathVariable
            Long rfidUid
    ) {
        return visitorService.getUserByCredentialRfidUid(rfidUid);
    }

    @PostMapping
    public UserDto createUser(
            @Valid
            @RequestBody
            CreateUserDto createUserDto
    ) {
        return visitorService.createUser(createUserDto);
    }


}
