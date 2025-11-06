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
  <label>件名：<input name="name" value="<%=data.get("name")%>" required></label><br>
  <label>日付：<input type="date" name="date" value="<%=data.get("date")%>" required></label><br>
  <label>時間：<input type="time" name="time" value="<%=data.get("time")%>" required></label><br>
  <label>メモ：<input name="note" value="<%=data.get("note")%>"></label><br>
  <p><button type="submit">更新</button></p>
</form>
<a href="${pageContext.request.contextPath}/list">戻る</a>
</body>
</html>