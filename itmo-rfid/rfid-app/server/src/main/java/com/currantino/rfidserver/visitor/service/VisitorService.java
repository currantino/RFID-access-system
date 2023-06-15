package com.currantino.rfidserver.visitor.service;

import com.currantino.rfidserver.exception.UserNotFoundException;
import com.currantino.rfidserver.visitor.controller.UserDto;
import com.currantino.rfidserver.visitor.dto.CreateVisitorDto;
import com.currantino.rfidserver.visitor.entity.Visitor;
import com.currantino.rfidserver.visitor.mapper.UserMapper;
import com.currantino.rfidserver.visitor.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class VisitorService {
    private final VisitorRepository visitorRepository;
    private final UserMapper userMapper;

    public UserDto getVisitorById(Long id) {
        return userMapper.toDto(visitorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Could not find the requested user!")));
    }


    public UserDto createUser(CreateVisitorDto createVisitorDto) {
        Visitor visitor = userMapper.toEntity(createVisitorDto);
        Visitor saved = visitorRepository.save(visitor);
        return userMapper.toDto(saved);
    }

    public UserDto getUserByCredentialRfidUid(Long rfidUid) {
        Visitor visitor = visitorRepository.findUserByCredentialRfidUid(rfidUid)
                .orElseThrow(() -> new UserNotFoundException("Could not find the owner of card with rfid uid=%d!"
                        .formatted(rfidUid)));
        return userMapper.toDto(visitor);
    }

    @Transactional
    public UserDto blockUser(String email) {
        Visitor visitor = visitorRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден."));
        visitor.setBlocked(true);
        Visitor saved = visitorRepository.save(visitor);
        return userMapper.toDto(saved);
    }

    @Transactional
    public UserDto unblockUser(String email) {
        Visitor visitor = visitorRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден."));
        visitor.setBlocked(false);
        Visitor saved = visitorRepository.save(visitor);
        return userMapper.toDto(saved);
    }
}
