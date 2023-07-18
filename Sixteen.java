
/*SERVLET
16. Create a Java servlet that uses session management to maintain the state of the
user across multiple requests. The servlet should store the user's name in a session
object and display it on multiple pages of the web application.
ans-:*/
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        HttpSession session = request.getSession();
        session.setAttribute("username", name);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
         out.println("<html>");
        out.println("<head><title>Welcome</title></head>");
        out.println("<body>");
        out.println("<h1>Welcome, " + name + "!</h1>");
        out.println("<a href=\"page1.html\">Page 1</a><br>");
        out.println("<a href=\"page2.html\">Page 2</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
In those HTML pages (page1.html and page2.html), you can retrieve the user's name from the session object using JavaScript or any server-side language you are using (e.g., JSP). Here's an example using JavaScript:
<!DOCTYPE html>
<html>
<head>
    <title>Page 1</title>
    <script>
        window.onload = function() {
            var username = "<%= session.getAttribute("username") %>";
            document.getElementById("username").textContent = username;
        }
    </script>
    </head>
<body>
    <h1>Welcome to Page 1</h1>
    <p>User: <span id="username"></span></p>
</body>
</html>
