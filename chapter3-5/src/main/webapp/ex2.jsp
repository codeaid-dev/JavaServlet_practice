<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = request.getParameter("message");
msg = msg != null ? msg : "メッセージなし";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習2</title>
</head>
<body>
<h2>実習2</h2>
<p>メッセージ：<%= msg %>
</body>
</html>