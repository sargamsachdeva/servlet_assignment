import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        String username=req.getParameter("username");
        String password=req.getParameter("password");

        if(Login.validate(username,password) &&(!(username.equals("") || password.equals("")))) {

            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("blog.html");
            requestDispatcher.forward(req,resp);

        }

        else {
            out.println("<h1 style=\"color:red\">Unable To login!! Please enter correct username and password</h1>");

        }

    }
}
