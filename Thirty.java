
/*30. Create a Spring Boot application that uses Spring Cloud Circuit Breaker to handle
failures in a REST API. The API should use a circuit breaker pattern to handle
timeouts and other errors.
ans-:*/
//Create a service class that implements the business logic for your REST API. This service class will use a circuit breaker to handle failures. You can use Spring Cloud Circuit Breaker annotations such as @CircuitBreaker and @Fallback to define the circuit breaker behavior.
@Service
public class MyService {
    @CircuitBreaker(fallbackMethod = "fallbackMethod")
    public ResponseEntity<String> makeApiCall() {
        // Make the API call here
        // If the API call fails, the circuit breaker will open and the fallback method will be called
        // Return the response entity
    }

    public ResponseEntity<String> fallbackMethod(Throwable throwable) {
        // Fallback logic when the circuit breaker is open or encounters an error
        // Return a fallback response entity
    }
}
//Create a REST controller class that handles the API endpoints. Inject the service and define the necessary methods.
@RestController
@RequestMapping("/api")
public class MyController {
     private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/make-api-call")
    public ResponseEntity<String> makeApiCall() {
        return myService.makeApiCall();
    }
}
//For Spring Cloud Netflix:
@SpringBootApplication
@EnableCircuitBreaker
public class MyAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }
}
//For Spring Cloud Resilience4j:
@SpringBootApplication
@EnableCircuitBreaker
public class MyAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }
}


