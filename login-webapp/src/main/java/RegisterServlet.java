import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter out = resp.getWriter();

        String name=req.getParameter("name");
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        if(!(name.equals("") || username.equals("") || password.equals(""))) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test", "root", "tiger");

                PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?)");

                ps.setString(1, name);
                ps.setString(2, username);
                ps.setString(3, password);

                ps.executeUpdate();
                ps.close();

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.html");
                requestDispatcher.forward(req, resp);

                out.println("<h1 style=\"color:green\">You have registered successfully</h1>");

            } catch (Exception e2) {


                out.println("<h1 style=\"color:red\">Unable to register....Try with another user name!!</h1>");
            }
        }
       // out.println("<h1 style=\"color:green\">You have registered successfully</h1>");
        //out.close();
        else {
            out.println("<h1 style=\"color:red\">Unable to register</h1>");
        }
    }

}



