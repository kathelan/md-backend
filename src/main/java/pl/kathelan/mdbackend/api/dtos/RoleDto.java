package pl.kathelan.mdbackend.api.dtos;

import lombok.Data;

@Data
public class RoleDto {

    private String name;
    private String description;
    private String createdAt;

}
