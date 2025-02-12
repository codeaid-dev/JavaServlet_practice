package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex4")
public class Ex4 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    if (application.getAttribute("history") != null) {
      application.removeAttribute("history");
      application.removeAttribute("win");
    }
    request.getRequestDispatcher("/WEB-INF/jsp/ex4.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Random random = new Random();
    String[] hands = { "ぐー", "ちょき", "ぱー" };
    String com = hands[random.nextInt(hands.length)];
    String you = request.getParameter("you");
    ServletContext application = getServletContext();
    Integer win = (Integer) application.getAttribute("win");
    Map<Integer, String> history = (Map<Integer, String>) application.getAttribute("history");
    if (history == null) {
      history = new HashMap<>();
      win = 0;
    }
    int times = history.size() + 1;
    if (times < 6) {
      String result = "";
      if (you.equals(com)) {
        result = "あいこ（" + you + "：" + com + "）";
      } else if ((you.equals(hands[0]) && com.equals(hands[1])) ||
          (you.equals(hands[1]) && com.equals(hands[2])) ||
          (you.equals(hands[2]) && com.equals(hands[0]))) {
        result = "あなたの勝ち（" + you + "：" + com + "）";
        win++;
      } else {
        result = "コンピューターの勝ち（" + you + "：" + com + "）";
      }
      history.put(times, result);
      application.setAttribute("history", history);
      application.setAttribute("win", win);
    }
    if (times >= 5) {
      request.setAttribute("total", "5戦中" + win + "勝です。");
    }

    request.getRequestDispatcher("/WEB-INF/jsp/ex4.jsp").forward(request, response);
  }

}
