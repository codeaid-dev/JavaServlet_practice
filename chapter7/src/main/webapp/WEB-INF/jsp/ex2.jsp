<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String[] fruits = (String[])request.getAttribute("fruits");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習2</title>
</head>
<body>
  <h2>実習2</h2>
  <ul>
  <% for (String fruit : fruits) { %>
  <li><%= fruit %></li>
  <% } %>
  </ul>
</body>
</html>