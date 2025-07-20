package Employee.management.ems_backend.controller;

import Employee.management.ems_backend.dto.EmployeeDto;
import Employee.management.ems_backend.entity.Employee;
import Employee.management.ems_backend.repository.EmployeeRepository;
import Employee.management.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController
{
    private EmployeeService employeeService;

    // Rest Api: create a new employee [POST]
    @PostMapping("create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
    {
        var savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") Long employeeId)
    {
        var existingEmployee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(existingEmployee, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees()
    {
        var existingEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(existingEmployees);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long employeeId){
        var updateded = employeeService.updateEmployee(employeeDto, employeeId);
        return new ResponseEntity<>(updateded, HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId)
    {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Deleted employee");
    }

}
