package Employee.management.ems_backend.service;

import Employee.management.ems_backend.dto.EmployeeDto;
import Employee.management.ems_backend.entity.Employee;

import java.util.List;

public interface EmployeeService {

    ///  Doc:
    EmployeeDto getEmployeeById(Long employeeId);

    /// Doc:
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    // Doc:
    List<EmployeeDto> getAllEmployees();

    // Doc:
    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId);

    // Doc:
    void deleteEmployee(Long employeeId);
}
