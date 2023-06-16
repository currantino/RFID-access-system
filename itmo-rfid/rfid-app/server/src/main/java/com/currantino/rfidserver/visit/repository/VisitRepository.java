package com.currantino.rfidserver.visit.repository;

import com.currantino.rfidserver.visit.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query(
            "SELECT " +
                    "v " +
                    "FROM " +
                    "   Visit v " +
                    "WHERE " +
                    "   v.visitor.id = :id " +
                    "ORDER BY " +
                    "   v.visitDatetime DESC ")
    List<Visit> getLatestVisitsByVisitorId(@Param("id") Long id);
}
