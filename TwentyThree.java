
/* 
23. Create a Spring MVC application that allows users to register and login. The
application should have a registration form that accepts user details and a login form
that authenticates users.
ans-:*/
//Create a User entity class that represents the user data. The entity class should have properties such as username, password, email, etc.
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    // Getters and setters
    }
//Create a UserRepository interface that extends JpaRepository to perform CRUD operations on the User entity.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
//Create a registration form (register.jsp) that allows users to enter their details such as username, password, and email. The form should be submitted to a controller endpoint.
<form method="post" action="/register">
    <label for="username">Username:</label>
    <input type="text" name="username" required>
    
    <label for="password">Password:</label>
    <input type="password" name="password" required>
    
    <label for="email">Email:</label>
    <input type="email" name="email" required>
    
    <button type="submit">Register</button>
</form>
//Create a UserController class to handle the registration process. Autowire the UserRepository and implement a method to handle the registration form submission.
@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        // Save the user to the database
        userRepository.save(user);
        return "redirect:/login";
    }
}
//Create a login form (login.jsp) that allows users to enter their username and password. The form should be submitted to a controller endpoint.
<form method="post" action="/login">
    <label for="username">Username:</label>
    <input type="text" name="username" required>
    
    <label for="password">Password:</label>
    <input type="password" name="password" required>
    
    <button type="submit">Login</button>
</form>
//Create a SecurityConfig class to configure Spring Security for authentication and authorization.
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> userRepository.findByUsername(username))
                .passwordEncoder(passwordEncoder());
            }

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http
                    .authorizeRequests()
                        .antMatchers("/register").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .permitAll()
                        .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll();
            }
        
            @Bean
            public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
            }
        }
        //Create a dashboard page (dashboard.jsp) that is accessible after successful login.
        <h2>Welcome, ${username}!</h2>
        
        <a href="/logout">Logout</a>

