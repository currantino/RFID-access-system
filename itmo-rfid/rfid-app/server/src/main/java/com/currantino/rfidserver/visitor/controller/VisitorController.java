package com.currantino.rfidserver.visitor.controller;

import com.currantino.rfidserver.visitor.dto.CreateVisitorDto;
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

@RestController("visitorController")
@RequiredArgsConstructor
@RequestMapping("/api/telegram/v1/visitors")
public class VisitorController {
    private final VisitorService visitorService;

    @PostMapping
    public UserDto createUser(
            @Valid
            @RequestBody
            CreateVisitorDto createVisitorDto
    ) {
        return visitorService.createUser(createVisitorDto);
    }

    @GetMapping("/{id}")
    public UserDto getVisitorById(
            @RequestParam
            Long id
    ) {
        return visitorService.getVisitorById(id);
    }

    @PostMapping("/{email}/block")
    public UserDto blockVisitor(
            @PathVariable(name = "email")
            String email
    ){
        return visitorService.blockUser(email);
    }

    @PostMapping("/{email}/unblock")
    public UserDto unblockVisitor(
            @PathVariable(name = "email")
            String email
    ){
        return visitorService.unblockUser(email);
    }

    @GetMapping("/rfid/{rfidUid}")
    public UserDto getUserByCredentialId(
            @PathVariable
            Long rfidUid
    ) {
        return visitorService.getUserByCredentialRfidUid(rfidUid);
    }


}
