
    //13. create a java program that connects to a PostgreSql database and executes a batch update .
    //the program should read the input from a file and insert it into the database using JDBC batch update
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Thirteen{
    private static final String DB_URL = "jdbc:postgresql://localhost:3306/saket";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "start6393";

    public static void main(String[] args) {
        String inputFile = "input.txt";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO mytable (column1, column2) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            int batchSize = 0;
            int totalRecords = 0;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Assuming column1 and column2 are of type text
                String column1Value = data[0];
                String column2Value = data[1];

                statement.setString(1, column1Value);
                statement.setString(2, column2Value);
                statement.addBatch();

                batchSize++;
                totalRecords++;

                if (batchSize == 1000) { // Execute batch every 1000 records
                    statement.executeBatch();
                    batchSize = 0;
                }
            }

            if (batchSize > 0) {
                statement.executeBatch();
            }

            System.out.println("Total records inserted: " + totalRecords);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


