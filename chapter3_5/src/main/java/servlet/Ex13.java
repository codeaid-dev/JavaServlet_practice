package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex13")
public class Ex13 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String start = request.getParameter("start");
    String end = request.getParameter("end");
    String error = "";
    String result = "";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日(E)", Locale.JAPANESE);
    LocalDate startDate = null;
    if (start != null && start.length() != 0) {
      startDate = LocalDate.parse(start);
      result += "開始日: " + startDate.format(formatter) + "<br>";
    } else {
      error += "開始日を入力してください。<br>";
    }
    LocalDate endDate = null;
    if (end != null && end.length() != 0) {
      endDate = LocalDate.parse(end);
      result += "終了日: " + endDate.format(formatter) + "<br>";
    } else {
      error += "終了日を入力してください。<br>";
    }
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習13</title>
        </head>
        <body>
          <h2>実習13</h2>
          <p>
          """);
    if (error.length() == 0) {
      out.println(result);
      if (endDate.isAfter(startDate)) {
        Period period = Period.between(startDate, endDate);
        out.println("期間: " + period.getYears() + "年" + period.getMonths() + "ヶ月" + period.getDays() + "日間");
      } else {
        out.println("開始日より後の日付を指定してください。<br>");
      }
    } else {
      out.println(error);
    }
    out.println("""
        </p>
        </body>
        </html>
        """);
  }

}
