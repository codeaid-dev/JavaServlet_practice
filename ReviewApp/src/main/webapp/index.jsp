<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String user = (String) session.getAttribute("user");
if (user != null) {
  session.invalidate();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品レビューアプリ - ユーザー設定</title>
</head>
<body>
<h2>商品レビューアプリ - ユーザー設定</h2>
<h3>ユーザー名を入力してください</h3>

<form action="user" method="post">
  <input type="text" name="user" placeholder="ユーザー名">
  <button type="submit">開始</button>
</form>

<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
  <p style="color:red;"><%= error %></p>
<% } %>

</body>
</html>