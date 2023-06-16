package com.currantino.rfidserver.visitor.service;

import com.currantino.rfidserver.exception.UserNotFoundException;
import com.currantino.rfidserver.visitor.dto.CreateVisitorDto;
import com.currantino.rfidserver.visitor.dto.UserDto;
import com.currantino.rfidserver.visitor.entity.Visitor;
import com.currantino.rfidserver.visitor.mapper.UserMapper;
import com.currantino.rfidserver.visitor.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        visitor.setBlocked(false);
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
    public UserDto blockUser(Long id) {
        Visitor visitor = visitorRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден."));
        visitor.setBlocked(true);
        Visitor saved = visitorRepository.save(visitor);
        return userMapper.toDto(saved);
    }

    @Transactional
    public UserDto unblockUser(Long id) {
        Visitor visitor = visitorRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден."));
        visitor.setBlocked(false);
        Visitor saved = visitorRepository.save(visitor);
        return userMapper.toDto(saved);
    }

    public List<UserDto> getVisitors() {
        return visitorRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteVisitorById(Long id) {
        if (!visitorRepository.existsById(id)) {
            throw new UserNotFoundException("Пользователь не найден.");
        }
        visitorRepository.deleteById(id);
    }
}
