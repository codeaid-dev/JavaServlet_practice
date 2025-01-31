package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Favorite;

@WebServlet("/ex11")
public class Ex11 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Favorite favorite = new Favorite();
    String[] sports = request.getParameterValues("sports");
    if (sports != null) {
      for (String s : sports) {
        favorite.setSport(s);
      }
    }
    String[] players = request.getParameter("players").split(",");
    for (String p : players) {
      favorite.setPlayer(p);
    }
    request.setAttribute("favorite", favorite);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex11.jsp");
    dispatcher.forward(request, response);
  }

}
