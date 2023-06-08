package com.currantino.rfidserver.visit.service;

import com.currantino.rfidserver.visit.dto.VisitDto;
import com.currantino.rfidserver.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;

    private final Map<String, Boolean> authorized = Map.of(
            "179 234 78 06", Boolean.TRUE
    );

    public boolean addVisit(VisitDto visitDto) {
        String rfidUid = visitDto.rfidUid();
        return authorized.containsKey(rfidUid) && authorized.get(rfidUid);
    }
}
