package com.currantino.rfidserver.person.repository;

import com.currantino.rfidserver.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
