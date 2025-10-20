package servlet;

import java.io.IOException;
import java.time.LocalDate;

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
    String date = request.getParameter("date");
    String result = "";
    int year, month, day;
    if (date != null && date.length() != 0) {
      String[] dates = date.split("-");
      year = Integer.parseInt(dates[0]);
      month = Integer.parseInt(dates[1]);
      day = Integer.parseInt(dates[2]);
      boolean leapYear = false;
      if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
        leapYear = true;
      }
      int monthLength;
      if (month == 4 || month == 6 || month == 9 || month == 11) {
        monthLength = 30;
      } else if (month == 2) {
        if (leapYear) {
          monthLength = 29;
        } else {
          monthLength = 28;
        }
      } else {
        monthLength = 31;
      }

      if (day < monthLength) {
        day += 1;
      } else {
        day = 1;
        if (month == 12) {
          month = 1;
          year += 1;
        } else {
          month += 1;
        }
      }
      result = String.format("次の日は「%d年%02d月%02d日」", year, month, day);

      LocalDate local = LocalDate.parse(date);
      local = local.plusDays(1);
      year = local.getYear();
      month = local.getMonthValue();
      day = local.getDayOfMonth();
      //      result = String.format("次の日は「%d年%02d月%02d日」", year, month, day);
    }
    request.setAttribute("result", result);
    request.getRequestDispatcher("/WEB-INF/jsp/ex1.jsp").forward(request, response);
  }

}
