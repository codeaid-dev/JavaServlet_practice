package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex3")
public class Ex3 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    if ("forward".equals(action)) {
      request.setAttribute("message", "これはフォワードで渡されたデータです");
      RequestDispatcher dispatcher = request.getRequestDispatcher("/ex3.jsp");
      dispatcher.forward(request, response);
    } else if ("redirect".equals(action)) {
      request.setAttribute("message", "これはリダイレクトで渡されたデータです");
      response.sendRedirect(request.getContextPath() + "/ex3.jsp");
    }
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>リクエストスコープ</title>
        </head>
        <body>
        """);
    out.println("<h2>リクエストスコープ</h2>");
    out.println("<p>URLにactionパラメーターを付けて<br>以下のいずれかの値でGETリクエストしてください。</p>");
    out.println("<ul><li>forward</li>");
    out.println("<li>redirect</li></ul>");
    out.println("""
        </body>
        </html>
        """);
  }

}
