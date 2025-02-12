package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex3")
public class Ex3 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/jsp/ex3.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String lower = "abcdefghijklmnopqrstuvwxyz";
    String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String numbers = "0123456789";
    String symbols = "!@#$%^&*()_+-=[]{};:,.<>?";
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    String count = request.getParameter("count");
    if (count != null) {
      int wc = Integer.valueOf(count);
      sb.append(String.valueOf(lower.charAt(random.nextInt(lower.length()))));
      sb.append(String.valueOf(upper.charAt(random.nextInt(upper.length()))));
      sb.append(String.valueOf(numbers.charAt(random.nextInt(numbers.length()))));
      sb.append(String.valueOf(symbols.charAt(random.nextInt(symbols.length()))));
      StringBuilder words = new StringBuilder(lower + upper + numbers + symbols);
      for (int i = 0; i < wc - 4; i++) {
        sb.append(String.valueOf(words.charAt(random.nextInt(words.length()))));
      }
      String password = shuffle(sb.toString());
      request.setAttribute("password", password);
      request.getRequestDispatcher("/WEB-INF/jsp/ex3.jsp").forward(request, response);
    }
  }

  String shuffle(String str) {
    ArrayList<Character> chars = new ArrayList<>();
    for (char c : str.toCharArray()) {
      chars.add(c);
    }
    Collections.shuffle(chars, new Random());

    StringBuilder shuffled = new StringBuilder();
    for (char c : chars) {
      shuffled.append(c);
    }
    return shuffled.toString();
  }
}
