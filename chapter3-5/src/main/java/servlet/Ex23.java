package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex23")
public class Ex23 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    int basePrice = Integer.parseInt(request.getParameter("basePrice"));
    int gPrice = Integer.parseInt(request.getParameter("gPrice"));
    int cPrice = Integer.parseInt(request.getParameter("cPrice"));
    int tPrice = Integer.parseInt(request.getParameter("tPrice"));
    int wPrice = Integer.parseInt(request.getParameter("wPrice"));
    String[] option = request.getParameterValues("option");
    int total = 0;
    if (option != null) {
      for (String item : option) {
        if (item.equals("goggles")) {
          total += gPrice;
        }
        if (item.equals("swimcap")) {
          total += cPrice;
        }
        if (item.equals("towel")) {
          total += tPrice;
        }
        if (item.equals("swimwear")) {
          total += wPrice;
        }
      }
    }
    total += basePrice;
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習23</title>
        </head>
        <body>
          <h2>実習23</h2>
          """);
    out.println("<p>合計金額: " + total + "円</p>");
    out.println("""
        </body>
        </html>
        """);
  }

}
