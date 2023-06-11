package com.currantino.rfidserver.credential.entity;

import com.currantino.rfidserver.user.entity.User;
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
    @Column(name = "is_valid")
    private Boolean valid;
    @OneToOne(mappedBy = "credential")
    private User user;

    public Boolean isValid() {
        return valid;
    }
}
