package com.currantino.rfidserver.person.controller;

import com.currantino.rfidserver.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

}
