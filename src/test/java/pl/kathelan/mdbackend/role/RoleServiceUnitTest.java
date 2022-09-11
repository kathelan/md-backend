package pl.kathelan.mdbackend.role;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.kathelan.mdbackend.api.dtos.RoleRequestDto;
import pl.kathelan.mdbackend.entities.Role;
import pl.kathelan.mdbackend.repositories.RoleRepository;
import pl.kathelan.mdbackend.services.RoleService;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class RoleServiceUnitTest {

    private RoleService roleService;
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        roleRepository = mock(RoleRepository.class);
        roleService = new RoleService(roleRepository);
    }

    @Test
    public void shouldReturnRole() {
        Role role = Role.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();

        Mockito.when(roleRepository.findById(any())).thenReturn(Optional.of(role));

        var result = roleService.getRoleById(any());

        Assertions.assertEquals(result, role);
    }

    @Test
    public void shouldReturnRoles() {
        Role role = Role.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();
        Role role1 = Role.builder()
                .id(2L)
                .name("name1")
                .description("description1")
                .build();
        List<Role> roles = List.of(role, role1);
        Mockito.when(roleRepository.findAll()).thenReturn(roles);

        var result = roleService.getRoles();

        Assertions.assertEquals(result, roles);
        Assertions.assertEquals(result.size(), 2);
    }

    @Test
    public void shouldSaveRole() {
        RoleRequestDto roleRequestDto = new RoleRequestDto();
        roleRequestDto.setName("name");
        roleRequestDto.setDescription("description");
        Mockito.when(roleRepository.save(any(Role.class))).thenReturn(any(Role.class));

        roleService.saveRole(roleRequestDto);

        Mockito.verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    public void shouldUpdate() {
        RoleRequestDto roleRequestDto = new RoleRequestDto();
        roleRequestDto.setName("name");
        roleRequestDto.setDescription("description");
        Role role = Role.builder().name("name1").description("desc").build();
        Mockito.when(roleRepository.save(any(Role.class))).thenReturn(any(Role.class));

        var result = roleService.updateRole(roleRequestDto, role);

        Mockito.verify(roleRepository, times(1)).save(any(Role.class));
        Assertions.assertNotEquals(result, role);
    }

}
