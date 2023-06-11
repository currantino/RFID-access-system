package com.currantino.rfidserver.user.service;

import com.currantino.rfidserver.exception.UserNotFoundException;
import com.currantino.rfidserver.user.controller.UserDto;
import com.currantino.rfidserver.user.dto.CreateUserDto;
import com.currantino.rfidserver.user.entity.User;
import com.currantino.rfidserver.user.mapper.UserMapper;
import com.currantino.rfidserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Could not find the requested user!")));
    }


    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.toEntity(createUserDto);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    public UserDto getUserByCredentialRfidUid(Long rfidUid) {
        User user = userRepository.findUserByCredentialRfidUid(rfidUid)
                .orElseThrow(() -> new UserNotFoundException("Could not find the owner of card with rfid uid=%d!"
                        .formatted(rfidUid)));
        return userMapper.toDto(user);
    }
}
