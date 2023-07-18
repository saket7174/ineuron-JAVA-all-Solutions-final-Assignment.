// 11. Write a Java program that connects to a MySQL database using JDBC. The program
// should read data from a table and display the results in the console.

import java.sql.*;

public class Eleventh{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:saket"; 
        String username = "root"; 
        String password = "start6393"; 

        try {
            // Step 1: Register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Create a connection to the database
            Connection connection =DriverManager.getConnection(url, username, password);

            // Step 3: Create a statement
            Statement statement = connection.createStatement();

            // Step 4: Execute the query
            String query = "SELECT * FROM saket"; 
            ResultSet resultSet = statement.executeQuery(query);

            // Step 5: Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); 
                String name = resultSet.getString("name"); 
                // Retrieve and process other columns as needed
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // Step 6: Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
    }
}


