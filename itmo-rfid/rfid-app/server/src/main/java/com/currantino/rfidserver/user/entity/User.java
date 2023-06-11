package com.currantino.rfidserver.user.entity;

import com.currantino.rfidserver.credential.entity.Credential;
import com.currantino.rfidserver.visit.entity.Visit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;
    @OneToOne()
    private Credential credential;
    @Column(name = "is_blocked")
    private Boolean blocked;
    @OneToMany(mappedBy = "id")
    private List<Visit> visits;

    public Boolean isBlocked() {
        return blocked;
    }
}
