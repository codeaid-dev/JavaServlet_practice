<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.DayOfWeek,java.time.LocalDate" %>
<%
int year = (int)request.getAttribute("year");
int month = (int)request.getAttribute("month");
LocalDate firstDay = LocalDate.of(year, month, 1);
LocalDate lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth());
DayOfWeek firstDayOfWeek = firstDay.getDayOfWeek();
int startOffset = firstDayOfWeek.getValue() % 7;
String[] week = { "日", "月", "火", "水", "木", "金", "土" };
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習10</title>
<style>
td {
  text-align: right;
  padding: 0 5px;
}
</style>
</head>
<body>
  <h2>実習10</h2>
  <p><%= year %>年<%= month %>月のカレンダー</p>
  <table>
  <tr>
  <% for (String w : week) { %>
    <th><%= w %></th>
  <% } %>
  </tr><tr>
  <% for (int i = 0; i < startOffset; i++) { %>
    <td></td>
  <% } %>
  <%
  LocalDate currentDate = firstDay;
  while (!currentDate.isAfter(lastDay)) {
    out.println("<td>" + currentDate.getDayOfMonth() + "</td>");
    if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
      out.println("</tr><tr>");
    }
    currentDate = currentDate.plusDays(1);
  }
  %>
  </table>
</body>
</html>