package pl.kathelan.mdbackend.api.dtos;

import lombok.Data;
import pl.kathelan.mdbackend.entities.Role;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.time.Instant;

@Data
public class EmployeeDto {

    private String firstName;
    private String lastName;
    private RoleDto roleDto;
    private Instant createdAt;
}
