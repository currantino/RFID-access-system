package com.currantino.rfidserver.visitor.repository;

import com.currantino.rfidserver.visit.entity.Visit;
import com.currantino.rfidserver.visitor.entity.Visitor;
import com.currantino.rfidserver.visitor.service.LatestVisitDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    @Query(
            """
                    SELECT
                        v
                    FROM
                        Visitor v
                    WHERE
                        v.credential.rfidUid = :rfidUid
                    """
    )
    Optional<Visitor> findUserByCredentialRfidUid(@Param("rfidUid") Long rfidUid);

    @Query("SELECT " +
            "   v " +
            "FROM " +
            "   Visitor v " +
            "WHERE " +
            "   v.email = :email")
    Optional<Visitor> findByEmail(@Param("email") String email);

}
