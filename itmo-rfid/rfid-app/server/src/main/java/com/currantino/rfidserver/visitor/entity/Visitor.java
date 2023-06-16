package com.currantino.rfidserver.visitor.entity;

import com.currantino.rfidserver.credential.entity.Credential;
import com.currantino.rfidserver.visit.entity.Visit;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "visitors")
@Getter
@Setter
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birthday")
    private LocalDateTime birthday;
    @Column(name = "email")
    private String email;
    @OneToOne()
    private Credential credential;
    @Column(name = "is_blocked")
    private Boolean blocked;
    @OneToMany(mappedBy = "id")
    private List<Visit> visits;

    public Boolean isBlocked() {
        return blocked;
    }

    public String getFullName() {
        return Strings.join(List.of(firstName, lastName), ' ');
    }
}
