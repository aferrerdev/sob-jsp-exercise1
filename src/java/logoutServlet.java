
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/logoutServlet"})
public class logoutServlet extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {
         doAction(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {
         doAction(request, response);
}

private void doAction(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {
         HttpSession session = request.getSession(false);
         if (session != null)
                 session.invalidate();
         response.sendRedirect("http://localhost:8080/sob-jsp-exercise1");
         //request.getRequestDispatcher("Votacion").forward(request, response);
}


}
