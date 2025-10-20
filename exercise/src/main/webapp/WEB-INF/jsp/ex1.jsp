<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>次の日はいつ？</title>
</head>
<body>
  <h2>次の日はいつ？</h2>
<form method="POST">
  <p><label>年月日：<input type="date" name="date" required></label></p>
  <button type="submit">表示</button>
</form>
  <%
  String result = (String)request.getAttribute("result");
  if (result != null) {
    %>
    <p><%= result %></p>
    <%
  } else {
  %>
  <p>ここに表示</p>
  <% } %>
</body>
</html>