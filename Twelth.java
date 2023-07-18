
//12.Java program that uses jdbc to implement simple CRUD  application.
//The program should allow user to add , view ,update and delete records in MY  Sql  database table
import java.sql.*;

public class Twelth {

    // JDBC database connection parameters
    static final String DB_URL = "jdbc:mysql://localhost/saket";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "start6393";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {//"com.mysql.jdbc.Driver"
            // Step 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Step 3: Create a statement
            stmt = conn.createStatement();

            // Step 4: Create table
            String createTableSql = "CREATE TABLE IF NOT EXISTS employees (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), age INT)";
            stmt.executeUpdate(createTableSql);
            System.out.println("Table created.");

            // Step 5: Insert data into table
            String insertSql = "INSERT INTO employees (name, age) VALUES ('John Doe', 25)";
            stmt.executeUpdate(insertSql);
            System.out.println("Data inserted.");

            // Step 6: Read data from table
            String selectSql = "SELECT id, name, age FROM employees";
            ResultSet rs = stmt.executeQuery(selectSql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
            rs.close();

            // Step 7: Update data in table
            String updateSql = "UPDATE employees SET age = 30 WHERE name = 'John Doe'";
            stmt.executeUpdate(updateSql);
            System.out.println("Data updated.");

            // Step 8: Delete data from table
            String deleteSql = "DELETE FROM employees WHERE name = 'John Doe'";
            stmt.executeUpdate(deleteSql);
            System.out.println("Data deleted.");

            // Step 9: Close the statement and connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}


