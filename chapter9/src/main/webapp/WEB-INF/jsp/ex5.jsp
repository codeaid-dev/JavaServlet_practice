<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習5</title>
</head>
<body>
  <h2>実習5</h2>
  <h3>🎲 数当てゲーム 🎲</h3>
  <p>0〜9の異なる3つの数字を当てよう！</p>
  <form action="ex5" method="post">
    <label>1つ目の数字: <input type="number" name="num1" min="0" max="9" required></label><br>
    <label>2つ目の数字: <input type="number" name="num2" min="0" max="9" required></label><br>
    <label>3つ目の数字: <input type="number" name="num3" min="0" max="9" required></label><br>
    <p><button type="submit">判定する</button></p>
  </form>

  <a href="/chapter9/ex5">
     <p><button>新しいゲームを始める</button></p>
  </a>

  <% 
  String message = (String) request.getAttribute("message");
  if (message != null) {
  %>
    <p><b><%= message %></b></p>
  <% } %>
  <%
  String yourNumbers = (String) request.getAttribute("yourNumbers");
  if (yourNumbers != null) {
  %>
    <p><%= yourNumbers %></p>
  <% } %>
  <%-- Degug: <%= application.getAttribute("targetNumbers") %> --%>
</body>
</html>