package com.currantino.rfidserver.credential.entity;

import com.currantino.rfidserver.visitor.entity.Visitor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "credentials")
@Getter
@Setter
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credential_id")
    private Long id;
    @Column(name = "rfid_uid")
    private Long rfidUid;
    @Column(name = "is_blocked")
    private Boolean blocked;
    @OneToOne(mappedBy = "credential")
    private Visitor visitor;

    public Boolean isBlocked() {
        return blocked;
    }
}
