package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/ex7")
public class Ex7 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    String age = request.getParameter("age");
    User user = new User();
    user.setName(name);
    user.setAge(Integer.valueOf(age));
    request.setAttribute("user", user);
    request.getRequestDispatcher("/WEB-INF/jsp/ex7.jsp").forward(request, response);
  }

}
