<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String strResult = (String)request.getAttribute("strResult");
String error = (String)request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習9</title>
</head>
<body>
  <h2>実習9</h2>
  <p>結果</p>
  <% if (strResult != null) { %>
  <p><%= strResult %></p>
  <% } %>
  <% if (error != null) { %>
  <p><%= error %></p>
  <% } %>
</body>
</html>