import joc.GameBean;

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
            Connection con = joc.DatabaseConnection.initializeDatabase();
            if (request.getParameter("user") == "" || request.getParameter("pass") == "") {
                response.setContentType("text/html;charset=UTF-8");
                request.setAttribute("errorMessage", "Please complete the inputs");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            PreparedStatement pst = con.prepareStatement("SELECT * FROM ACCOUNTS WHERE USERNAME=? AND PASSWORD=?");
            pst.setString(1, request.getParameter("user"));
            pst.setString(2, request.getParameter("pass"));
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                request.setAttribute("errorMessage", "No such of account in the db");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/join.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    public String getServletInfo() {
        return "Process initial form response.";
    }
}
