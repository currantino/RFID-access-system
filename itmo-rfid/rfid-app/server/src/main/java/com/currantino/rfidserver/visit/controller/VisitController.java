package com.currantino.rfidserver.visit.controller;

import com.currantino.rfidserver.visit.dto.CreateVisitDto;
import com.currantino.rfidserver.visit.dto.VisitDto;
import com.currantino.rfidserver.visit.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/visit")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;


    @PostMapping
    public ResponseEntity<VisitDto> addVisit(
            @RequestBody
            CreateVisitDto createVisitDto
    ) {
        log.info("Received rfid uid: {}", createVisitDto.rfidUid());
        VisitDto visitDto = visitService.newVisit(createVisitDto);
        log.info("""
                        Rfid uid: {}
                        owner: {}
                        PASSED
                        """,
                createVisitDto.rfidUid(),
                visitDto.visitor().fullName());
        return ResponseEntity.ok(visitDto);
    }

}