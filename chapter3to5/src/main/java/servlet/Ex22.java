package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex22")
public class Ex22 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String moji = request.getParameter("moji");
    int size = Integer.parseInt(request.getParameter("size"));
    String color = request.getParameter("color");
    boolean bold = false;
    if (request.getParameter("bold") != null) {
      bold = true;
    }
    int total = Integer.parseInt(request.getParameter("total"));
    int line = Integer.parseInt(request.getParameter("line"));
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習22</title>
        <style>
        div {
            """);
    out.println("font-size:" + size + "px;");
    out.println("color:" + color + ";");
    if (bold) {
      out.println("font-weight:bold;");
    }
    out.println("""
        }
        </style>
        </head>
        <body>
          <h2>実習22</h2>
            """);
    out.println("<div>");
    int row = total / line;
    int rest = total % line;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < line; j++) {
        out.print(moji + " ");
      }
      out.println("<br>");
    }
    for (int i = 0; i < rest; i++) {
      out.print(moji + " ");
    }
    out.println("</div>");
    out.println("""
        </body>
        </html>
        """);
  }

}
