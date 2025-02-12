package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex1")
public class Ex1 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/jsp/ex1.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String height = request.getParameter("height");
    String weight = request.getParameter("weight");
    height = height != null ? height : "0.0";
    weight = weight != null ? weight : "0.0";
    double h = Double.valueOf(height);
    double w = Double.valueOf(weight);
    double bmi = (double) w / (Math.pow(h / 100, 2));
    String result = "";
    if (bmi < 16) {
      result = "痩せすぎ";
    } else if (bmi >= 16.0 && bmi <= 16.99) {
      result = "痩せ";
    } else if (bmi >= 17.0 && bmi <= 18.49) {
      result = "痩せぎみ";
    } else if (bmi >= 18.50 && bmi <= 24.99) {
      result = "普通体重";
    } else if (bmi >= 25.0 && bmi <= 29.99) {
      result = "前肥満";
    } else if (bmi >= 30.0 && bmi <= 34.99) {
      result = "肥満(1度)";
    } else if (bmi >= 35.0 && bmi <= 39.99) {
      result = "肥満(2度)";
    } else {
      result = "肥満(3度)";
    }
    result = String.format("身長%.2fcm 体重%.2fkg<br>BMI値: %.2f 判定: " + result, h, w, bmi);
    request.setAttribute("result", result);
    request.getRequestDispatcher("/WEB-INF/jsp/ex1.jsp").forward(request, response);
  }

}
