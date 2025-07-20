package Employee.management.ems_backend.service.imp;

import Employee.management.ems_backend.dto.EmployeeDto;
import Employee.management.ems_backend.entity.Employee;
import Employee.management.ems_backend.exception.ResourceNotFoundException;
import Employee.management.ems_backend.mapper.EmployeeMapper;
import Employee.management.ems_backend.repository.EmployeeRepository;
import Employee.management.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImp implements EmployeeService
{
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto getEmployeeById(Long employeeId)
    {
        var employeeFound = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee is not registered"));
        return EmployeeMapper.mapToEmployeeDto(employeeFound);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        var employee = EmployeeMapper.mapToEmployee(employeeDto);
        var employeeSaved = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(employeeSaved);
    }

    @Override
    public List<EmployeeDto> getAllEmployees()
    {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId) {
        var existingEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Not Found the employee"));

        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());

        var updated = employeeRepository.save(existingEmployee);

        return EmployeeMapper.mapToEmployeeDto(updated);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        var existingEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Not Found the employee"));

        employeeRepository.deleteById(existingEmployee.getId());
    }
}
