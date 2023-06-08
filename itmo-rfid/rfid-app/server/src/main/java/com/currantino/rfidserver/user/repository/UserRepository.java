package com.currantino.rfidserver.user.repository;

import com.currantino.rfidserver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
