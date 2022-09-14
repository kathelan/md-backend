package pl.kathelan.mdbackend.api.controllers;

import liquibase.pro.packaged.E;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kathelan.mdbackend.api.dtos.EmployeeDto;
import pl.kathelan.mdbackend.api.dtos.EmployeeRequestDto;
import pl.kathelan.mdbackend.api.dtos.RoleDto;
import pl.kathelan.mdbackend.api.dtos.RoleRequestDto;
import pl.kathelan.mdbackend.entities.Employee;
import pl.kathelan.mdbackend.entities.Role;
import pl.kathelan.mdbackend.services.EmployeeService;
import pl.kathelan.mdbackend.services.RoleService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    protected ModelMapper modelMapper = new ModelMapper();


    @GetMapping("{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @GetMapping
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.saveEmployee(employeeRequestDto);
        return new ResponseEntity<EmployeeDto>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateRole(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            Employee updateEmployee = employeeService.updateEmployee(employeeRequestDto, employee);
            EmployeeDto result = modelMapper.map(updateEmployee, EmployeeDto.class);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
