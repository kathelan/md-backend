package pl.kathelan.mdbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kathelan.mdbackend.api.dtos.RoleRequestDto;
import pl.kathelan.mdbackend.entities.Role;
import pl.kathelan.mdbackend.repositories.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role with such id not found"));
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public void saveRole(RoleRequestDto roleRequestDto) {
        Role role = Role.builder()
                .name(roleRequestDto.getName())
                .description(roleRequestDto.getDescription())
                .createdAt(Instant.now())
                .build();
        log.info("created Role with name: {}", role.getName());
        roleRepository.save(role);

    }

    public Role updateRole(RoleRequestDto roleRequestDto, Role role) {
        String name = role.getName();
        role.setName(roleRequestDto.getName());
        role.setDescription(roleRequestDto.getDescription());
        role.setUpdatedAt(Instant.now());

        log.info("updated Role : {} with name: {}", name, role.getName());
        return roleRepository.save(role);
    }
}
