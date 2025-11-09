package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex24-confirm")
public class Ex24Confirm extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    name = name != null ? name : "";
    email = email != null ? email : "";
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>サーブレットとJSP基本</title>
        </head>
        <body>
          """);
    out.println("<h2>サーブレットとJSP基本:確認画面</h2>");
    out.println("<form action='ex24-submit' method='post'>");
    out.println("<p>名前: " + name + "</p>");
    out.println("<input type='hidden' name='name' value='" + name + "'>");
    out.println("<p>メールアドレス: " + email + "</p>");
    out.println("<input type='hidden' name='email' value='" + email + "'>");
    out.println("<button type='submit'>確認</button>");
    out.println("</form>");
    out.println("""
        </body>
        </html>
        """);
  }

}
