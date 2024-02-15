import com.employee.projection.controller.RestController;
import com.employee.projection.model.Department;
import com.employee.projection.model.Employee;
import com.employee.projection.service.EmployeeDataException;
import com.employee.projection.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class RestControllerTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private RestController restController;

    @Test
    public void returnEmpoyeeInfo() throws EmployeeDataException {
        Department department = new Department();
        department.setDepartmentId(1);
        department.setName("Финансовый отдел");

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setFirstName("Иван");
        employee.setLastName("Сидоров");
        employee.setPosition("Бухгалтер");
        employee.setSalary(12500.0);
        employee.setDepartment(department);

        Mockito.when(employeeService.getEmployeeInfo(1)).thenReturn(employee);
        ResponseEntity<Object> response = restController.getEmployeeInfo(1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(new ResponseEntity<>(employee, HttpStatus.OK), restController.getEmployeeInfo(1));
    }

    @Test
    public void addNewBook() throws EmployeeDataException {
        Department department = new Department();
        department.setDepartmentId(1);
        department.setName("Финансовый отдел");

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setFirstName("Иван");
        employee.setLastName("Сидоров");
        employee.setPosition("Бухгалтер");
        employee.setSalary(12500.0);
        employee.setDepartment(department);

        Mockito.when(employeeService.addNewEmployee("Иван", "Сидоров",
                "Бухгалтер", 12500.0, 1)).thenReturn(employee);
        ResponseEntity<Object> response = restController.addNewEmployee("Иван", "Сидоров",
                "Бухгалтер", 12500.0, 1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(new ResponseEntity<>(employee, HttpStatus.CREATED),
                restController.addNewEmployee("Иван", "Сидоров",
                        "Бухгалтер", 12500.0, 1));
    }

    @Test
    public void deleteEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);

        ResponseEntity response = restController.deleteEmployee(1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateBook() throws EmployeeDataException {
        String newLastName = "Кротов";

        Department department = new Department();
        department.setDepartmentId(1);
        department.setName("Финансовый отдел");

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setFirstName("Иван");
        employee.setLastName("Сидоров");
        employee.setPosition("Бухгалтер");
        employee.setSalary(12500.0);
        employee.setDepartment(department);

        Mockito.when(employeeService.updateEmployee(1, "Иван", newLastName,
                "Бухгалтер", 12500.0, 1)).thenReturn(new Employee("Иван", newLastName,
                "Бухгалтер", 12500.0, department));
        ResponseEntity<Object> response = restController.updateEmployee(1, "Иван", newLastName,
                "Бухгалтер", 12500.0, 1);

        employee.setLastName(newLastName);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}