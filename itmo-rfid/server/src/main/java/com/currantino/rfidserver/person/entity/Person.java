package com.currantino.rfidserver.person.entity;

import com.currantino.rfidserver.credential.entity.Credential;
import com.currantino.rfidserver.visit.entity.Visit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
    @OneToOne()
    private Credential rfid;
    @OneToMany(mappedBy = "id")
    private List<Visit> visits;

}
