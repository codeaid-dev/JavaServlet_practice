package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex5")
public class Ex5 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<h2>実習5</h2>");
    int uranai = (int) (Math.random() * 10);
    if (uranai == 0) {
      out.println("<p>今日は最高！</p>");
    } else if (uranai >= 1 && uranai <= 3) {
      out.println("<p>今日はそこそこです</p>");
    } else if (uranai >= 4 && uranai <= 7) {
      out.println("<p>今日はまぁまぁ</p>");
    } else {
      out.println("<p>今日は最悪</p>");
    }
  }

}
