
/*20. The program should use Hibernate to map the table to a Java object and then update
the data in the table. After updating the data, the program should retrieve it from the
database and display it on the console. */
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
Then, retrieve the entity object to be updated, modify its properties, and use the session to update the entity in the database.
public class HibernateExample {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            // Retrieve the entity to be updated
            Employee employee = session.get(Employee.class, 1L);
            if (employee != null) {
                // Update the properties
                employee.setName("John Smith");
                  employee.setSalary(BigDecimal.valueOf(6000));

                session.update(employee);
                transaction.commit();

                // Retrieve and display the updated data
                List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
                for (Employee emp : employees) {
                    System.out.println("ID: " + emp.getId());
                    System.out.println("Name: " + emp.getName());
                    System.out.println("Salary: " + emp.getSalary());
                    System.out.println("--------------------");
                }
            }

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

