
/*21. Create a Spring Boot application that inserts data into a MySQL database table using
JPA and Hibernate. The application should use Spring Data JPA to map the table to a
Java object and then insert the data into the table.
ans-: */
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal salary;

    // Getters and setters
}
//Create a Spring Data JPA repository interface that extends the JpaRepository interface. This interface will provide the necessary CRUD operations for your entity.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
//Create a service class that uses the repository to insert data into the database. Autowire the repository in the service class and define a method to save an employee object.
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
     public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
//Create a controller class that handles HTTP requests. Autowire the service class in the controller and define a method to handle the request to insert employee data.
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
}

