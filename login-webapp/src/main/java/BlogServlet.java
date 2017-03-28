import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BlogServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        String blogid=req.getParameter("blogid");
        String blogname=req.getParameter("blogname");
        HttpSession session = req.getSession(false);

        String username=(String) session.getAttribute("username");
        System.out.println(username);
        if(!(blogid.equals("") || blogname.equals(""))) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test", "root", "tiger");


                PreparedStatement ps = con.prepareStatement("insert into blog values(?,?,?)");

                ps.setString(1, blogid);
                ps.setString(2, blogname);
                ps.setString(3, username);

                ps.executeUpdate();
                ps.close();
                out.println("<h1 style=\"color:green\"> Done successfully</h1>");

            } catch (Exception e2) {


                out.println("<h1 style=\"color:red\">Error!!</h1>");
            }
        }
        else {
            out.println("<h1 style=\"color:red\">Provide blog id and name properly!!</h1>");
        }

    }
}
