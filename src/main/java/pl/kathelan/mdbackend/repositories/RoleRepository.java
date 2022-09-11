package pl.kathelan.mdbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kathelan.mdbackend.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
