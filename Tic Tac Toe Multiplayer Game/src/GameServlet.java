import joc.GameBean;
import joc.GameBean.GamePlayer;
import joc.SessionCounter;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static joc.GameBean.GamePlayer.*;

@WebServlet(name = "GameServlet", urlPatterns = "/GameServlet")
public class GameServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GameBean game = (GameBean) request.getServletContext().getAttribute("game1");
        HttpSession session = request.getSession();

        int line = Integer.parseInt(request.getParameter("joc.Line"));
        int col = Integer.parseInt(request.getParameter("Col"));

        boolean u = (boolean) session.getAttribute("userNumber");


        if (u) {
            game.playPlayerTurn(line, col);
            GamePlayer winner = game.getWinner();
            switch (winner) {
                case NOBODY:
                    if (game.hasEmptyCell()) {
                        switch (game.getWinner()) {
                            case NOBODY:
                                break;
                            case COMPUTER:
                                request.setAttribute("winner", "You lost");
                                request.getSession(false);
                                break;
                            case USER:
                                request.setAttribute("winner", "You won");
                                request.getSession(false);
                                break;
                        }
                    }
                    break;
                case COMPUTER:
                    request.setAttribute("winner", "You lost");
                    request.getSession(false);
                    break;
                case USER:
                    request.setAttribute("winner", "You won");
                    request.getSession(false);
                    break;
            }
            if (winner == NOBODY && !game.hasEmptyCell()) {
                request.setAttribute("winner", "Personne");
            }
            response.setIntHeader("Refresh", 2);
            response.setContentType("text/html");
            request.getRequestDispatcher("/game.jsp").forward(request, response);
        } else {
            game.playComputerTurn(line, col);
            GamePlayer winner = game.getWinner();
            switch (winner) {
                case NOBODY:
                    if (game.hasEmptyCell()) {
                        switch (game.getWinner()) {
                            case NOBODY:
                                break;
                            case COMPUTER:
                                request.setAttribute("winner", "You won");
                                request.getSession(false);
                                break;
                            case USER:
                                request.setAttribute("winner", "You lost");
                                request.getSession(false);
                                break;
                        }
                    }
                    break;
                case COMPUTER:
                    request.setAttribute("winner", "You won");
                    request.getSession(false);
                    break;
                case USER:
                    request.setAttribute("winner", "You lost");
                    request.getSession(false);
                    break;
            }
            if (winner == NOBODY && !game.hasEmptyCell()) {
                request.setAttribute("winner", "Nobody won");
                request.getSession(false);
            }
            response.setIntHeader("Refresh", 2);
            response.setContentType("text/html");
            request.getRequestDispatcher("/game.jsp").forward(request, response);
        }


        request.getRequestDispatcher("/game.jsp").forward(request, response);
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
        return "Short description";
    }

}