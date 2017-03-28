import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

    public static boolean validate(String username, String password) {

        boolean status = false;
        PrintWriter out;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test","root","tiger");

            PreparedStatement ps=con.prepareStatement("select * from user where username=? and password=?");

            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet resultSet = ps.executeQuery();
            status = resultSet.next();

        }catch (Exception e2) {


            e2.printStackTrace();
        }

       return status;

    }
}
