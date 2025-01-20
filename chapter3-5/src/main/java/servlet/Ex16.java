package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex16")
public class Ex16 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String strYear = request.getParameter("year");
    String strMonth = request.getParameter("month");
    strYear = (strYear != null && strYear.length() != 0) ? strYear : "2020";
    strMonth = (strMonth != null && strMonth.length() != 0) ? strMonth : "1";
    int year = Integer.parseInt(strYear);
    int month = Integer.parseInt(strMonth);
    // 指定した年月の最初の日と最後の日を取得
    LocalDate firstDay = LocalDate.of(year, month, 1);
    LocalDate lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth());
    // 最初の日の曜日を取得
    DayOfWeek firstDayOfWeek = firstDay.getDayOfWeek();
    int startOffset = firstDayOfWeek.getValue() % 7; // 日曜日を0とする
    String[] week = { "日", "月", "火", "水", "木", "金", "土" };
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習16</title>
        <style>
        td {
          text-align: right;
          padding: 0 5px;
        }
        </style>
        </head>
        <body>
          <h2>実習16</h2>
          """);
    out.println("<p>" + strYear + "年" + strMonth + "月のカレンダー</p>");
    out.println("""
        <table>
          <tr>
        """);
    // 曜日を表示
    for (String w : week) {
      out.println("<th>" + w + "</th>");
    }
    out.println("</tr><tr>");
    // 曜日の位置を調整
    for (int i = 0; i < startOffset; i++) {
      out.println("<td></td>");
    }
    // 各日付を表示
    LocalDate currentDate = firstDay;
    while (!currentDate.isAfter(lastDay)) {
      out.println("<td>" + currentDate.getDayOfMonth() + "</td>");

      // 土曜日で次の行
      if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
        out.println("</tr><tr>");
      }

      // 次の日に進む
      currentDate = currentDate.plusDays(1);
    }
    out.println("""
          </tr>
        </table>
        </body>
        </html>
        """);
  }

}
