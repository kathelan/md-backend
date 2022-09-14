package pl.kathelan.mdbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kathelan.mdbackend.api.dtos.EmployeeRequestDto;
import pl.kathelan.mdbackend.api.dtos.RoleRequestDto;
import pl.kathelan.mdbackend.entities.Employee;
import pl.kathelan.mdbackend.entities.Role;
import pl.kathelan.mdbackend.repositories.EmployeeRepository;
import pl.kathelan.mdbackend.repositories.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleService roleService;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with such id not found"));
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployee(EmployeeRequestDto employeeRequestDto) {
        Role role = getRole(employeeRequestDto);

        Employee employee = Employee.builder()
                .firstName(employeeRequestDto.getFirstName())
                .lastName(employeeRequestDto.getLastName())
                .role(role)
                .createdAt(Instant.now())
                .build();

        log.info("created Employee with firstname: {} and lastName: {}", employee.getFirstName(), employee.getLastName());
        employeeRepository.save(employee);

    }

    public Employee updateEmployee(EmployeeRequestDto employeeRequestDto, Employee employee) {
        Role role = getRole(employeeRequestDto);

        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employee.setRole(role);
        employee.setUpdatedAt(Instant.now());

        log.info("updated Employee with name: {}", employee.getFirstName() + " " + employee.getLastName());
        return employeeRepository.save(employee);
    }

    private Role getRole(EmployeeRequestDto employeeRequestDto) {
        return roleService.getRoleById(employeeRequestDto.getRoleId());
    }
}
