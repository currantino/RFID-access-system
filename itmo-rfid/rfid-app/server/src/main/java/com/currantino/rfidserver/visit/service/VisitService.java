package com.currantino.rfidserver.visit.service;

import com.currantino.rfidserver.exception.AccessDeniedException;
import com.currantino.rfidserver.exception.UserNotFoundException;
import com.currantino.rfidserver.user.entity.User;
import com.currantino.rfidserver.user.repository.UserRepository;
import com.currantino.rfidserver.visit.VisitMapper;
import com.currantino.rfidserver.visit.dto.CreateVisitDto;
import com.currantino.rfidserver.visit.dto.VisitDto;
import com.currantino.rfidserver.visit.entity.Visit;
import com.currantino.rfidserver.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final VisitMapper visitMapper;


    private final Map<String, Boolean> authorized = Map.of(
            "179 234 78 06", Boolean.TRUE
    );

    @Transactional
    public VisitDto newVisit(CreateVisitDto createVisitDto) {
        User visitor = userRepository
                .findUserByCredentialRfidUid(createVisitDto.rfidUid())
                .orElseThrow(() -> new UserNotFoundException("Could not find existing user!"));
        Visit visit = Visit.builder()
                .visitor(visitor)
                .credential(visitor.getCredential())
                .time(LocalDateTime.now())
                .build();
        if (!visitor.isBlocked() || !visitor.getCredential().isValid()) {
            visit.setPassed(false);
            throw new AccessDeniedException("You have no access to the wanted territory.");
        }
        visit.setPassed(true);
        Visit saved = visitRepository.save(visit);
        return visitMapper.toDto(saved);
    }
}
