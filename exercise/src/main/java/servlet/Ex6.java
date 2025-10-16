package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex6")
public class Ex6 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>アルファベットクイズ</title>
        </head>
        <body>
        <h2>❓ アルファベットクイズ ❓</h2>
        <form method="POST">
          <p>隠すアルファベットの数：</p>
          <p><label><input type="radio" name="chars" value=1 checked>1文字</label>
          <label><input type="radio" name="chars" value=2>2文字</label>
          <label><input type="radio" name="chars" value=3>3文字</label></p>
          <p><button type="submit" name="question">出題</button></p>
        </form>
        </body>
        </html>
        """);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    StringBuilder question = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    String strAnswer = request.getParameter("answer");
    String strChars = request.getParameter("chars");
    if (strChars != null) {
      int chars = Integer.valueOf(strChars);
      List<Character> correct = new ArrayList<>();
      for (int i = 0; i < chars; i++) {
        Random random = new Random();
        int index = random.nextInt(question.length());
        correct.add(question.charAt(index));
        question.deleteCharAt(index);
      }
      application.setAttribute("question", question);
      application.setAttribute("correct", correct);
      application.setAttribute("start", System.currentTimeMillis());
      request.getRequestDispatcher("/WEB-INF/jsp/ex6.jsp").forward(request, response);
    } else if (strAnswer != null) {
      List<Character> correct = (List<Character>) application.getAttribute("correct");
      String[] answers = strAnswer.split(",");
      int count = 0;
      for (String answer : answers) {
        answer = answer.toUpperCase();
        if (answer.strip().length() == 1
            && correct.contains(Character.valueOf(answer.charAt(0)))) {
          count++;
        }
      }
      String result = "";
      if (count == correct.size()) {
        result = "正解です : " + correct.toString() + "<br>";
        long end = System.currentTimeMillis();
        long spend = end - (Long) application.getAttribute("start");
        result += "経過時間 : " + spend / 1000 + "秒";
      } else {
        result = "不正解です";
      }
      request.setAttribute("result", result);
      request.getRequestDispatcher("/WEB-INF/jsp/ex6.jsp").forward(request, response);
    }
  }

}
