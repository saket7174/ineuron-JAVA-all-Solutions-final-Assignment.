
/*18. Create a Java program that uses Hibernate to connect to a MySQL database and
retrieve data from a table. The program should use Hibernate to map the table to a
Java object and then display the data on the console.

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
Then, use the session to query the database and retrieve the data.
public class HibernateExample {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
             List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
            for (Employee employee : employees) {
                System.out.println("ID: " + employee.getId());
                System.out.println("Name: " + employee.getName());
                System.out.println("Salary: " + employee.getSalary());
                System.out.println("--------------------");
            }

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

