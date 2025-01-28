package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex9")
public class Ex9 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int num1 = Integer.parseInt(request.getParameter("num1"));
      int num2 = Integer.parseInt(request.getParameter("num2"));
      int num3 = Integer.parseInt(request.getParameter("num3"));
      String operator1 = request.getParameter("operator1");
      String operator2 = request.getParameter("operator2");
      int result = 0;
      if (operator1.equals("+")) {
        result = num1 + num2;
      } else if (operator1.equals("-")) {
        result = num1 - num2;
      } else if (operator1.equals("*")) {
        result = num1 * num2;
      } else if (operator1.equals("/")) {
        result = num1 / num2;
      }
      if (operator2.equals("+")) {
        result += num3;
      } else if (operator2.equals("-")) {
        result -= num3;
      } else if (operator2.equals("*")) {
        result *= num3;
      } else if (operator2.equals("/")) {
        result /= num3;
      }

      String strResult = num1 + operator1 + num2 + operator2 + num3 + "=" + result;
      request.setAttribute("strResult", strResult);
    } catch (NumberFormatException e) {
      request.setAttribute("error", "すべての入力値は整数である必要があります。");
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex9.jsp");
    dispatcher.forward(request, response);
  }

}
