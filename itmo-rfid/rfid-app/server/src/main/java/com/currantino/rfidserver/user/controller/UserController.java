package com.currantino.rfidserver.user.controller;

import com.currantino.rfidserver.user.dto.CreateUserDto;
import com.currantino.rfidserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JdbcTemplate jdbc;

    @GetMapping("/hello")
    public String hello() {
        try (
                Connection connection = DriverManager.getConnection(
                        System.getenv("SPRING_DATASOURCE_URL"),
                        System.getenv("SPRING_DATASOURCE_USERNAME"),
                        System.getenv("SPRING_DATASOURCE_PASSWORD")
                );) {
            PreparedStatement ps = connection.prepareStatement("SELECT version()");
            ResultSet rs = ps.executeQuery();
            StringBuilder result = new StringBuilder();
            while (rs.next()) {
                result.append(rs.getString(1));
                result.append("\n");
            }
            return result.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public UserDto getUserById(
            @RequestParam
            Long id
    ) {
        return userService.getUserById(id);
    }

    @GetMapping("/{rfidUid}")
    public UserDto getUserByCredentialId(
            @PathVariable
            Long rfidUid
    ) {
        return userService.getUserByCredentialRfidUid(rfidUid);
    }

    @PostMapping
    public UserDto createUser(
            @Valid
            @RequestBody
            CreateUserDto createUserDto
    ) {
        return userService.createUser(createUserDto);
    }


}
