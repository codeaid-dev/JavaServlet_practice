package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex4")
public class Ex4 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String url = request.getRequestURL().toString();
    String method = request.getMethod();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習4</title>
        </head>
        <body>
        """);
    out.println("<h2>実習4</h2>");
    out.println("<p>URL：" + url + "</p>");
    out.println("<p>メソッド：" + method + "</p>");
    out.println("""
        </body>
        </html>
        """);
  }

}
