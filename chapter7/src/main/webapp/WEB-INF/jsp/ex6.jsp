<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
User user = (User)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>リクエストスコープ</title>
</head>
<body>
  <h2>リクエストスコープ</h2>
  <p>ユーザー名: <%= user.getName() %></p>
  <p>年齢: <%= user.getAge() %></p>
  <p>メールアドレス: <%= user.getEmail() %></p>
</body>
</html>