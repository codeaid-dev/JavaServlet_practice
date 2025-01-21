package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex17")
public class Ex17 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習17</title>
        </head>
        <body>
          <h2>実習17</h2>
          <form action="ex17" method="post">
            <label>パスワード:<br>
            <input type="text" name="password"></label><br>
            <p><button type="submit">登録</button><p>
          </form>
        </body>
        </html>
        """);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String password = request.getParameter("password");
    password = password != null && password.length() != 0 ? password : "";
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習17</title>
        </head>
        <body>
          <h2>実習17</h2>
          <form action="ex17" method="post">
            <label>パスワード:<br>
            <input type="text" name="password"
            """);
    out.println("value=\"" + password + "\"></label><br>");
    out.println("""
          <p><button type="submit">登録</button></p>
        </form>
        """);
    if (password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!-/:-@\\[-`{-~])[!-~]{8,32}")) {
      out.println("<p>登録可能です。</p>");
    } else {
      out.println("<p>8文字以上32文字以下で英大小文字、数字、記号を1文字以上含んでください。</p>");
    }
    out.println("""
        </body>
        </html>
        """);
  }

}
