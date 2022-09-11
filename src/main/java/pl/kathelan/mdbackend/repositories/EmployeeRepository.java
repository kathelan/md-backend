package pl.kathelan.mdbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kathelan.mdbackend.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
