package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex5")
public class Ex5 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();

    if (application.getAttribute("targetNumbers") != null) {
      application.removeAttribute("targetNumbers");
    }
    List<Integer> numbers = new ArrayList<>();
    Random random = new Random();

    while (numbers.size() < 3) {
      int num = random.nextInt(10); // 0〜9のランダムな数字
      if (!numbers.contains(num)) {
        numbers.add(num);
      }
    }

    application.setAttribute("targetNumbers", numbers);

    request.getRequestDispatcher("/WEB-INF/jsp/ex5.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    List<Integer> targetNumbers = (List<Integer>) application.getAttribute("targetNumbers");

    if (targetNumbers == null) {
      response.sendRedirect("/chapter9/ex5");
      return;
    }

    int[] userGuess = new int[3];
    try {
      userGuess[0] = Integer.parseInt(request.getParameter("num1"));
      userGuess[1] = Integer.parseInt(request.getParameter("num2"));
      userGuess[2] = Integer.parseInt(request.getParameter("num3"));
    } catch (NumberFormatException e) {
      request.setAttribute("message", "数字を入力してください！");
      request.getRequestDispatcher("/WEB-INF/jsp/ex5.jsp").forward(request, response);
      return;
    }
    if (userGuess[0] == userGuess[1] || userGuess[0] == userGuess[2] || userGuess[1] == userGuess[2]) {
      request.setAttribute("message", "異なる数字を入力してください！");
      request.getRequestDispatcher("/WEB-INF/jsp/ex5.jsp").forward(request, response);
      return;
    }
    request.setAttribute("yourNumbers", "あなたが選んだ数字: " + userGuess[0] + "," + userGuess[1] + "," + userGuess[2]);
    int correct = 0;
    int partial = 0;
    System.out.println(targetNumbers); // for debug
    for (int i = 0; i < 3; i++) {
      int c = targetNumbers.get(i);
      if (userGuess[i] == c) {
        correct++; // 完全一致
      }
    }
    for (int i = 0; i < 3; i++) {
      int c = targetNumbers.get(i);
      if ((userGuess[i] != c) && targetNumbers.contains(userGuess[i])) {
        partial++; // 位置違い
      }
    }
    if (correct == 3) {
      application.removeAttribute("targetNumbers");
      request.setAttribute("message", "🎉 正解！ゲームクリア！");
    } else {
      request.setAttribute("message", "✅ 完全一致: " + correct + " 個, 🔄 部分一致: " + partial + " 個");
    }

    request.getRequestDispatcher("/WEB-INF/jsp/ex5.jsp").forward(request, response);
  }

}
