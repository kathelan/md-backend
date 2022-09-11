package pl.kathelan.mdbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToOne
    private Role role;

    @Column
    private Instant createdAt;
}
