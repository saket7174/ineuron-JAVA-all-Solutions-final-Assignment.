
/*25. Create a Spring Boot application that uses Spring AOP to log method calls. The
application should have a service class with methods that perform operations. The
application should use Spring AOP to log the method calls with input and output
parameters to the console.
ans-:*/
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

