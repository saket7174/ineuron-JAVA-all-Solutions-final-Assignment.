
/*27. Create a Spring Boot application that uses Spring Cloud to register a service with
Eureka Server. The application should expose a REST API for retrieving data from a
database and the API should be discovered by Eureka Server.
ans-:*/
@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
//Configure the connection to the Eureka Server by adding the following properties to your application.properties file:
spring.application.name=product-service
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/

Create a model class that represents the product entity. Annotate the class with @Entity to map it to a database table, and use appropriate annotations to define the table columns.

@Entity
@Table(name = "products")
public class Product {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    // Add other fields, getters, and setters
}
//Create a repository interface that extends JpaRepository to perform CRUD operations on the product entity.
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
//Create a service class that implements the business logic for retrieving products. Inject the repository and implement the necessary methods.
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
     public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }
}
//Create a REST controller class to handle the API endpoints for retrieving products. Inject the service and implement the necessary methods.
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}

