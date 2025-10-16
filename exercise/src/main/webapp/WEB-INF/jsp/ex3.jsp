<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード生成</title>
</head>
<body>
  <h2>パスワード生成</h2>
  <p>文字数は8~32の整数を入力してください。</p>
  <form method="post">
    <label>文字数: <input type="number" name="count" min="8" max="32" required></label>
    <p><button type="submit">生成</button></p>
  </form>
  <%
  String password = (String)request.getAttribute("password");
  if (password != null) {
  %>
  <p>生成したパスワード</p>
  <p><%= password %></p>
  <% } %>
</body>
</html>