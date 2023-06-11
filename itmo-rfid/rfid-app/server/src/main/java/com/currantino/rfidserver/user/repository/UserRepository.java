package com.currantino.rfidserver.user.repository;

import com.currantino.rfidserver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(
            """
                    SELECT
                        u
                    FROM
                        User u
                    WHERE
                        u.credential.rfidUid = :rfidUid
                    """
    )
    Optional<User> findUserByCredentialRfidUid(@Param("rfidUid") Long rfidUid);
}
