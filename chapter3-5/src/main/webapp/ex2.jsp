<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String msg = request.getParameter("message"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>実習2</h2>
<p>メッセージ：<%= (msg != null ? msg : "メッセージなし") %>
</body>
</html>