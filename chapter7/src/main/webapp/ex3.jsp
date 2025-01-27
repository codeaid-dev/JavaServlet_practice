<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習3</title>
</head>
<body>
  <h2>実習3</h2>
  <p><%= msg %></p>
</body>
</html>