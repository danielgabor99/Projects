import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Login",urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            Connection con = webapp.DatabaseConnection.initializeDatabase();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM login WHERE username=? AND password=?");
            pst.setString(1, request.getParameter("user"));
            pst.setString(2, request.getParameter("pass"));
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                request.setAttribute("errorMessage", "No such of account in the db");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            request.setAttribute("userid",rs.getString("username"));
            request.getRequestDispatcher("/show.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
