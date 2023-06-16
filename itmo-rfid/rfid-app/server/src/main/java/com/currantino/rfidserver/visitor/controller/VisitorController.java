package com.currantino.rfidserver.visitor.controller;

import com.currantino.rfidserver.visitor.dto.CreateVisitorDto;
import com.currantino.rfidserver.visitor.dto.UserDto;
import com.currantino.rfidserver.visitor.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("visitorController")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"*"}
)
@RequestMapping("/api/v1/visitors")
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

    @GetMapping
    public List<UserDto> getVisitors() {
        return visitorService.getVisitors();
    }

    @PostMapping("/{id}/block")
    public UserDto blockVisitor(
            @PathVariable(name = "id")
            Long id
    ) {
        return visitorService.blockUser(id);
    }

    @PostMapping("/{id}/unblock")
    public UserDto unblockVisitor(
            @PathVariable(name = "id")
            Long id
    ) {
        return visitorService.unblockUser(id);
    }

    @GetMapping("/rfid/{rfidUid}")
    public UserDto getUserByCredentialId(
            @PathVariable
            Long rfidUid
    ) {
        return visitorService.getUserByCredentialRfidUid(rfidUid);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitorById(
            @PathVariable(name = "id")
            Long id
    ) {
        visitorService.deleteVisitorById(id);
        return ResponseEntity.noContent().build();
    }


}
