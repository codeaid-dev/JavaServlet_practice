package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex20")
public class Ex20 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String answer = request.getParameter("answer");
    String question = request.getParameter("question");
    String correct = request.getParameter("correct");
    answer = (answer != null) ? answer : "";
    question = (question != null) ? question : "";
    correct = (correct != null) ? correct : "";
    boolean seikai = (answer.equals(correct) && answer.length() != 0) ? true : false;
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習20</title>
        </head>
        <body>
          <h2>実習20</h2>
            """);
    out.println("<p>" + question + "の県庁所在地は「" + answer + "」です。</p>");
    if (seikai) {
      out.println("<p style=\"font-size:30px;\">正解です。</p>");
    } else {
      out.println("<p style=\"font-size:30px;\">不正解：正解は「" + correct + "」です。</p>");
    }
    out.println("<a href=\"ex20.jsp\">次の問題</a>");
    out.println("""
        </body>
        </html>
        """);
  }

}
