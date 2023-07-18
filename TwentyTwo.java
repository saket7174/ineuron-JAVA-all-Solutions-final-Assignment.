
/*22. Create a Spring Boot application that uses Spring Data JPA to retrieve data from a
database. The application should have entities for users and orders, and should
allow for querying orders by user.
ans-:*/
User entity:
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters and setters
}
Order entity:
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
 @JoinColumn(name = "user_id")
    private User user;

    private String product;

    // Getters and setters
}
Create Spring Data JPA repositories for the user and order entities.
User repository:
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
Order repository:
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
//Create a service class to handle business logic. Autowire the repositories in the service class and define methods for retrieving users and orders.
@Service
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
     public OrderService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        return orderRepository.findByUser(user);
    }
}
//Create a controller class to handle HTTP requests. Autowire the service class in the controller and define endpoints for retrieving users and querying orders by user.
@RestController
@RequestMapping("/users")
public class UserController {
    private final OrderService orderService;

    @Autowired
    public UserController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return orderService.getAllUsers();
    }

    @GetMapping("/{userId}/orders")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }
}
