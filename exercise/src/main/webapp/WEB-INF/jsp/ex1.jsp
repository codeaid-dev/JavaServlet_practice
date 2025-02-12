<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習1</title>
</head>
<body>
  <h2>実習1</h2>
  <h3>あなたのBMI値</h3>
  <form action="ex1" method="post">
    <label>身長(cm): <input type="number" step="0.01" name="height" required></label><br>
    <label>体重(kg): <input type="number" step="0.01" name="weight" required></label><br>
    <p><button type="submit">測定</button></p>
  </form>
  <%
  String result = (String)request.getAttribute("result");
  if (result != null) {
    %>
    <p><%= result %></p>
    <%
  }
  %>
</body>
</html>