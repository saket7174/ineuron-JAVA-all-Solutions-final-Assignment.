
/*29. Create a Spring Boot application that uses Spring Data JPA to retrieve data from a
database and expose it as a REST API. The API should allow for filtering, sorting,
and paging.
ans-:*/
//Create a model class that represents your data entity. Annotate the class with @Entity to map it to a database table, and use appropriate annotations to define the table columns.
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
//Create a repository interface that extends JpaRepository or any other appropriate repository interface provided by Spring Data JPA. This interface will handle the database operations for your entity.
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

    public List<Product> getProductsByFilter(String name, double minPrice, double maxPrice) {
        return productRepository.findByNameContainingAndPriceBetween(name, minPrice, maxPrice);
    }

    // Add other methods for sorting and paging as per your requirements
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

    @GetMapping("/search")
    public List<Product> getProductsByFilter(@RequestParam(required = false) String name,
                                             @RequestParam(required = false) Double minPrice,
                                             @RequestParam(required = false) Double maxPrice) {
        return productService.getProductsByFilter(name, minPrice, maxPrice);
    }

    // Add other methods for sorting and paging as per your requirements
}

