package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ex1welcome")
public class Ex1Welcome extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String user = request.getParameter("user");
    String password = request.getParameter("password");
    if ("1234".equals(password)) {
      HttpSession session = request.getSession(); //trueと同じ
      session.setAttribute("user", user); // セッションに保存
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex1.jsp"); // ログイン成功
      dispatcher.forward(request, response);
    } else {
      response.sendRedirect("ex1.html"); // ログイン失敗
    }
  }

}
