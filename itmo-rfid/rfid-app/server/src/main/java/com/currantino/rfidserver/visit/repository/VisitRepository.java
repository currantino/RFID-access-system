package com.currantino.rfidserver.visit.repository;

import com.currantino.rfidserver.visit.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitRepository extends JpaRepository<Visit, Long> {
}
