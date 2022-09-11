package pl.kathelan.mdbackend.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Table(name = "role")
@Data
@Builder
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public Role() {
    }
}
