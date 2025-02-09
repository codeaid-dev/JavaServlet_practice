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

@WebServlet("/ex4")
public class Ex4 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/jsp/ex4.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    ServletContext application = getServletContext();
    Random random = new Random();
    String[] result = new String[3];
    for (int i = 0; i < 3; i++) {
      result[i] = String.valueOf(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
    }

    String message;
    if (result[0].equals(result[1]) && result[1].equals(result[2])) {
      message = "🎉 大当たり！ (" + result[0] + result[1] + result[2] + ")";
    } else if (result[0].equals(result[1]) || result[1].equals(result[2]) || result[0].equals(result[2])) {
      message = "✨ 惜しい！ (" + result[0] + result[1] + result[2] + ")";
    } else {
      message = "💀 ハズレ！ (" + result[0] + result[1] + result[2] + ")";
    }

    List<String> history = (List<String>) application.getAttribute("history");
    if (history == null) {
      history = new ArrayList<>();
      application.setAttribute("history", history);
    }

    history.add(0, message); // 最新の結果をリストの先頭に追加
    if (history.size() > 10) {
      history.remove(10); // 履歴は最大10件まで
    }

    request.getRequestDispatcher("/WEB-INF/jsp/ex4.jsp").forward(request, response);
  }

}
