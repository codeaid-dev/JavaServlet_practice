package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex10")
public class Ex10 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String station = request.getParameter("station");
    String type = "";
    if (station.equals("A") || station.equals("C") || station.equals("D")) {
      type = "快速";
    } else if (station.equals("B")) {
      type = "快速・急行";
    } else if (station.equals("E")) {
      type = "快速・急行・特急";
    }
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>サーブレットとJSP基本</title>
        </head>
        <body>
          <h2>停車駅</h2>
          """);
    out.println("<p>" + station + "駅には" + type + "の電車が止まります。</p>");
    out.println("""
        </body>
        </html>
        """);
  }

}
