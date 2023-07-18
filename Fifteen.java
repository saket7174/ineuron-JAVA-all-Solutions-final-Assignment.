
/*15.Write a Java servlet that reads the data from a MySQL database table and displays it
in an HTML table on the web page. The servlet should use JDBC to connect to the
database and retrieve the data.*/

@WebServlet("/displayData")
public class DisplayDataServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Establish the database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement for executing SQL queries
            Statement statement = connection.createStatement();

            // Execute the SQL query to retrieve data
            String sql = "SELECT * FROM your_table_name";
            ResultSet resultSet = statement.executeQuery(sql);

            // Generate the HTML table
            out.println("<html><body><table border='1'><tr><th>ID</th><th>Name</th></tr>");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                out.println("<tr><td>" + id + "</td><td>" + name + "</td></tr>");
            }
            out.println("</table></body></html>");

            // Close the database resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("An error occurred while retrieving data.");
        }
    }
}

