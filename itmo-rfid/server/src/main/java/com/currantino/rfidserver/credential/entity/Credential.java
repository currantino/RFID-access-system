package com.currantino.rfidserver.credential.entity;

import com.currantino.rfidserver.person.entity.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "credential")
@Getter
@Setter
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credential_id", nullable = false)
    private Long id;
    @Column(name = "rfid_uid", nullable = false, unique = true)
    private Long rfidUid;
    @OneToOne(mappedBy = "rfid")
    private Person person;
}
