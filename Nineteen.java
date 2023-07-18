
import java.math.BigDecimal;

public class Nineteen {
    
}
/*19. Create a Java program that uses Hibernate to insert data into a MySQL database
table. The program should use Hibernate to map the table to a Java object and then
insert the data into the table. After inserting the data, the program should retrieve it
from the database and display it on the console.

ans-:*/
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
Then, create an instance of the entity class, set its properties, and use the session to save the entity to the database.

public class HibernateExample {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Employee employee = new Employee();
            employee.setName("John Doe");
            employee.setSalary(BigDecimal.valueOf(5000));

            session.save(employee);

            transaction.commit();

            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
            for (Employee emp : employees) {
                System.out.println("ID: " + emp.getId());
                System.out.println("Name: " + emp.getName());
                System.out.println("Salary: " + emp.getSalary());
                System.out.println("--------------------");
            }

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }}}

