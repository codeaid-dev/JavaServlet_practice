package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex10")
public class Ex10 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Integer year = Integer.valueOf(request.getParameter("year"));
    Integer month = Integer.valueOf(request.getParameter("month"));
    request.setAttribute("year", year);
    request.setAttribute("month", month);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex10.jsp");
    dispatcher.forward(request, response);
  }

}
