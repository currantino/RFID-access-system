package com.currantino.rfidserver.visit.entity;

import com.currantino.rfidserver.credential.entity.Credential;
import com.currantino.rfidserver.visitor.entity.Visitor;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "visits")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long id;

    @Column(name = "visit_datetime")
    private LocalDateTime visitDatetime;
    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;
    @ManyToOne
    @JoinColumn(name = "credential_id")
    private Credential credential;
    @Column(name = "is_passed")
    private Boolean passed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Visit visit = (Visit) o;
        return getId() != null && Objects.equals(getId(), visit.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean isPassed() {
        return passed;
    }
}
