<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String result = (String)request.getAttribute("result");
String email = (String)request.getAttribute("email");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習8</title>
</head>
<body>
  <h2>実習8</h2>
  <p>メールアドレス: <%= email %></p>
  <p>結果: <%= result %>
</body>
</html>