package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex24-submit")
public class Ex24Submit extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習24</title>
        </head>
        <body>
          """);
    out.println("<h2>実習24:登録完了</h2>");
    out.println("<p>名前: " + name + "</p>");
    out.println("<p>メールアドレス: " + email + "</p>");
    out.println("""
        </body>
        </html>
        """);
  }

}
