
/*28. Create a Spring Boot application that uses Spring Cloud Config Server to externalise
configuration. The application should have a property file that defines properties for
database connection and other application settings.
ans-:*/
//Enable the Config Server in your main application class by adding the @EnableConfigServer annotation.

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
//Configure the Config Server properties in your application.properties file. Specify the Git repository URL that contains your configuration files.
spring.application.name=config-server
server.port=8888
spring.cloud.config.server.git.uri=<git-repo-url>
//Create a bootstrap.properties file in your application's resources directory. This file is used to specify the Config Server URL and the application name.
spring.application.name=myapp
spring.cloud.config.uri=http://localhost:8888
Use the configuration properties in your application by injecting them using the @Value annotation or using the @ConfigurationProperties annotation.
@RestController
public class MyController {
    @Value("${database.url}")
    private String databaseUrl;
  @GetMapping("/database-url")
    public String getDatabaseUrl() {
        return databaseUrl;
    }
}

