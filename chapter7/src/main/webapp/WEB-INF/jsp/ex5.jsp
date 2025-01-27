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
  <p>ユーザー名: <%= request.getAttribute("username") %></p>
  <p>年齢: <%= request.getAttribute("age") %></p>
  <p>メールアドレス: <%= request.getAttribute("email") %></p>
</body>
</html>