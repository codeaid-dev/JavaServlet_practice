package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ex2")
public class Ex2 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj != null && sessionObj.getAttribute("user") != null) {
      response.sendRedirect("ex2welcome");
      return;
    }
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習2</title>
        </head>
        <body>
        <h2>実習2</h2>
        <form action="ex2welcome" method="post">
          <label>ユーザー: <input type="text" name="user" required></label><br>
          <label>パスワード: <input type="password" name="password" required></label>
          <p><button type="submit">ログイン</button></p>
        </form>
        </body>
        </html>
        """);
  }

}
