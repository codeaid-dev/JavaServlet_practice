<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String[] fruits = (String[])request.getAttribute("fruits");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>リクエストスコープ</title>
</head>
<body>
  <h2>リクエストスコープ</h2>
  <ul>
  <% for (String fruit : fruits) { %>
  <li><%= fruit %></li>
  <% } %>
  </ul>
</body>
</html>