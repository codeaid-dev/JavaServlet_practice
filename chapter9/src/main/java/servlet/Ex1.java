package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex1")
public class Ex1 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    ServletContext application = getServletContext();
    List<String> visitors = (List<String>) application.getAttribute("visitors");
    if (visitors == null) {
      visitors = new ArrayList<>();
    }
    String ip = request.getRemoteAddr();
    visitors.add(ip);
    application.setAttribute("visitors", visitors);
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>アプリケーションスコープ</title>
        </head>
        <body>
        <h2>アプリケーションスコープ</h2>
        """);
    if (!visitors.isEmpty()) {
      out.println("<p>訪問IPアドレス</p>");
      out.println("<ul>");
      for (String n : visitors) {
        out.println("<li>" + n + "</li>");
      }
      out.println("</ul>");
    }
    out.println("""
        </body>
        </html>
        """);
  }

}
