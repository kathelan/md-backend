package pl.kathelan.mdbackend.api.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class EmployeeRequestDto {

    private String firstName;
    private String lastName;
    private Long RoleId;
    private Instant createdAt;
}
