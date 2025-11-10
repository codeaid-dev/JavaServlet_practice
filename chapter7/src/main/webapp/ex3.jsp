<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>リクエストスコープ</title>
</head>
<body>
  <h2>リクエストスコープ</h2>
  <p><%= msg %></p>
</body>
</html>