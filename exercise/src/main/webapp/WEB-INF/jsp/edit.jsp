<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%
  Map<String,String> data = (Map<String,String>)request.getAttribute("data");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約編集</title>
</head>
<body>
<h2>予約編集</h2>
<p style="color:red;">${requestScope.error}</p>
<form method="post" action="${pageContext.request.contextPath}/update">
  <input type="hidden" name="id" value="<%=data.get("id")%>">
  件名：<input name="name" value="<%=data.get("name")%>" required><br>
  日付：<input type="date" name="date" value="<%=data.get("date")%>" required><br>
  時間：<input type="time" name="time" value="<%=data.get("time")%>" required><br>
  メモ：<input name="note" value="<%=data.get("note")%>"><br>
  <input type="submit" value="更新">
</form>
<a href="${pageContext.request.contextPath}/list">戻る</a>
</body>
</html>