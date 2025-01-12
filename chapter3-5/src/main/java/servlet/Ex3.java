package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Ex3")
public class Ex3 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    LocalDateTime today = LocalDateTime.now();
    String result = today.format(dtf);
    out.println("<h2>実習3</h2>");
    out.println("<p>今日の日付(年月日)：" + result + "</p>");
  }

}