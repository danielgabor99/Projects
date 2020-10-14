import joc.GameBean;
import joc.SessionCounter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author Karl
 */
@WebServlet(name = "EntryServlet", urlPatterns = "/EntryServlet")
public class EntryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session = request.getSession();
        SessionCounter counter = (SessionCounter) session.getAttribute(
                SessionCounter.COUNTER);
        if (counter.getActiveSessionNumber() > 3) {
            request.setAttribute("errorMessage", "Max Players!!!");
            request.getRequestDispatcher("/join.jsp").forward(request, response);
        }
        String user = request.getParameter("User");
        boolean userFirst;
        if (user != null) userFirst = true;
        else userFirst = false;

        ServletContext application = getServletConfig().getServletContext();

        GameBean game = (GameBean) application.getAttribute("gameBean");

        if (counter.getActiveSessionNumber() - 1 == 2) {
            game.setStartByUser(userFirst);
            game.startGame();
            session.setAttribute("userNumber", userFirst);
            session.setAttribute("userName", user);
            request.getServletContext().setAttribute("game1", game);
            request.getRequestDispatcher("/game.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Wait for the other playersss!!!");
            request.getRequestDispatcher("/join.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Process initial form response.";
    }

}