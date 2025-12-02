package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex3secure")
public class Ex3Secure extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>フィルター・リスナー</title>
        </head>
        <body>
        <h2>フィルター・リスナー</h2>
        <h3 style="color:red;">アクセス成功</h3>
        <p>このページは許可されたIPアドレスからのみアクセスできます。</p>
        </body>
        </html>
        """);
  }

}
