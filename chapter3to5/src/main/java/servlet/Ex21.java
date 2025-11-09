package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex21")
public class Ex21 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String text = request.getParameter("text");
    text = text != null ? text : "";
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>サーブレットとJSP基本</title>
        <style>
        table,th,td {
          border-collapse: collapse;
          border: 1px solid;
        }
        </style>
        </head>
        <body>
          <h2>サーブレットとJSP基本</h2>
            """);
    String[] lines = text.split("\n");
    out.println("<table>");
    for (int i = 0; i < lines.length; i++) {
      String[] row = lines[i].split(",");
      out.println("<tr>");
      for (int j = 0; j < row.length; j++) {
        if (i == 0) {
          out.println("<th>" + row[j] + "</th>");
        } else {
          out.println("<td>" + row[j] + "</td>");
        }
      }
      out.println("</tr>");
    }
    out.println("</table>");
    out.println("""
        </body>
        </html>
        """);
  }

}
