
/*24.Create a Spring Boot application that uses Spring MVC to create a REST API. The
API should accept a JSON request with data and insert it into a MySQL database
table using JPA and Hibernate. The application should use Spring Data JPA to map
the table to a Java object and then insert the data into the table.

ans-:*/
//Configure the database connection properties in the application.properties file:
spring.datasource.url=jdbc:mysql://localhost:3306/db_name
spring.datasource.username=db_username
spring.datasource.password=db_password
spring.jpa.hibernate.ddl-auto=update

//Create a model class that represents the data you want to insert into the database. Annotate the class with @Entity to map it to a database table, and use appropriate annotations to define the table columns and relationships.
@Entity
@Table(name = "your_table_name")
public class YourModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Add your fields and getters/setters here
    }
//Create a repository interface that extends JpaRepository to perform CRUD operations on your model class.
@Repository
public interface YourRepository extends JpaRepository<YourModel, Long> {
}
Create a REST controller class to handle the API endpoints for inserting data. Inject the repository and implement the necessary methods.
@RestController
@RequestMapping("/api")
public class YourController {
    private final YourRepository yourRepository;

    public YourController(YourRepository yourRepository) {
        this.yourRepository = yourRepository;
    }

    @PostMapping("/insert")
    public YourModel insertData(@RequestBody YourModel model) {
        return yourRepository.save(model);
    }
}
Example JSON payload:
{
    "field1": "value1",
    "field2": "value2"
  }
  25. Create a Spring Boot application that uses Spring AOP to log method calls. The
  application should have a service class with methods that perform operations. The
  application should use Spring AOP to log the method calls with input and output
  parameters to the console.
  ans-:
  //Create a service class that contains the methods you want to log. For example, let's create a UserService class with a getUserById method:
  @Service
  public class UserService {
      public User getUserById(Long id) {
          // Perform some logic to fetch user from the database
          User user = // Fetch user from the database
  
          return user;
            }
}
//Create an aspect class that implements the logging logic using Spring AOP. Annotate the class with @Aspect and define pointcuts and advice for the methods you want to intercept and log. Let's create a LoggingAspect class:
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.app.UserService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("Method name: " + methodName);
        System.out.println("Arguments: " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "execution(* com.example.app.UserService.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("Method name: " + methodName);
        System.out.println("Returned value: " + result);
    }
}

