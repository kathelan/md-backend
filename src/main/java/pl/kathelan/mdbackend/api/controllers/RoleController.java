package pl.kathelan.mdbackend.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kathelan.mdbackend.api.dtos.RoleDto;
import pl.kathelan.mdbackend.api.dtos.RoleRequestDto;
import pl.kathelan.mdbackend.entities.Role;
import pl.kathelan.mdbackend.services.RoleService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController("/roles")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;
    protected ModelMapper modelMapper = new ModelMapper();


    @GetMapping("{id}")
    public RoleDto getRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        return modelMapper.map(role, RoleDto.class);
    }

    @GetMapping
    public List<RoleDto> getRoles() {
        List<Role> roles = roleService.getRoles();
        return roles.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody RoleRequestDto roleRequestDto) {
        roleService.saveRole(roleRequestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @RequestBody RoleRequestDto roleRequestDto) {
        try {
            Role role = roleService.getRoleById(id);
            Role updateRole = roleService.updateRole(roleRequestDto, role);
            RoleDto result = modelMapper.map(updateRole, RoleDto.class);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
