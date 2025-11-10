<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList" %>
<%
String strMonth = request.getParameter("month");
String strDate = request.getParameter("date");
int month = (strMonth != null && strMonth.length() != 0) ? Integer.parseInt(strMonth) : 1;
int date = (strDate != null && strDate.length() != 0) ? Integer.parseInt(strDate) : 1;
String[] seiza = {"山羊座","水瓶座","魚座","牡羊座","牡牛座","双子座","蟹座","獅子座","乙女座","天秤座","蠍座","射手座"};
int[] last = {19,18,20,19,20,21,22,22,22,23,22,21};
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サーブレットとJSP基本</title>
</head>
<body>
  <h2>星座</h2>
  <form action="ex19.jsp" method="post">
    <select name="month">
      <% for (int i = 1; i < 13; i++) { %>
        <option value=<%= String.valueOf(i) %>
        <%= (i == month) ? "selected" : "" %>>
        <%= String.valueOf(i) %></option>
      <% } %>
    </select>
    <label for="month">月</label>
    <select name="date">
      <% for (int i = 1; i < 32; i++) { %>
        <option value=<%= String.valueOf(i) %>
        <%= (i == date) ? "selected" : "" %>>
        <%= String.valueOf(i) %></option>
      <% } %>
    </select>
    <label for="date">日</label>
    <p><button type="submit">送信</button></p>
  </form>
  <%
  String msg = "";
  if (request.getMethod().equals("POST")) {
    List<Integer> sm = new ArrayList<>(List.of(4,6,9,11));
    if (month == 2 && date >= 29) {
        date = 29;
    } else if (sm.contains(month) && date >= 31) {
        date = 30;
    }
    if (last[month-1] >= date) {
        msg = month+"月"+date+"日は"+seiza[month-1]+"です";
    } else {
        msg = month+"月"+date+"日は"+seiza[month%12]+"です";
    }
  }
  %>
  <p><%= msg %></p>
</body>
</html>