
/*14. Create a Java servlet that reads the name of the user from a form and displays a
welcome message on the web page. The servlet should use the GET method to read
the input data from the user.
ans-:*/
import java.io.IOException;
import java.io.PrintWriter;

public class Fourteen extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the name parameter from the request
        String name = request.getParameter("name");

        // Set the content type of the response
        response.setContentType("text/html");

        // Get the PrintWriter object from the response
        PrintWriter out = response.getWriter();
  // Write the HTML response
        out.println("<html>");
        out.println("<head><title>Welcome</title></head>");
        out.println("<body>");
        out.println("<h1>Welcome, " + name + "!</h1>");
        out.println("</body>");
        out.println("</html>");
    }

// To test the servlet, you can create an HTML form that sends a GET request to the servlet with the user's name as a parameter. Here's an example HTML form:
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Form</title>
</head>
<body>
    <form action="WelcomeServlet" method="GET">
        <label for="name">Enter your name:</label>
        <input type="text" id="name" name="name" required>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
