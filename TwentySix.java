
/*26. Create a Spring Boot application that exposes a REST API for managing a list of
products. The API should allow for creating, updating, deleting, and retrieving
products.
ans-:*/
//Create a model class that represents the product entity. Annotate the class with @Entity to map it to a database table, and use appropriate annotations to define the table columns.
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
//Create a service class that implements the business logic for managing products. Inject the repository and implement the necessary methods.
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

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        // Update other fields if necessary
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
//Create a REST controller class to handle the API endpoints for managing products. Inject the service and implement the necessary methods.
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

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

