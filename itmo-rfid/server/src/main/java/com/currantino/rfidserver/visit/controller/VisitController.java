package com.currantino.rfidserver.visit.controller;

import com.currantino.rfidserver.visit.dto.VisitDto;
import com.currantino.rfidserver.visit.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/visit")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;


    @PostMapping
    public ResponseEntity<Void> addVisit(
            @RequestBody
            VisitDto visitDto
    ) {
        log.info("Received rfid: {}", visitDto.rfidUid());
        if (visitService.addVisit(visitDto)) {
            return new ResponseEntity<>(OK);
        }
        return new ResponseEntity<>(FORBIDDEN);
    }

}