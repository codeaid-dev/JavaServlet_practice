<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String source = (String)application.getAttribute("source");
  String result = (String)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ファイル入出力</title>
</head>
<body>
<h1>ファイル入出力</h1>
<h2>ファイル読み込み</h2>
<% if (source != null) { %>
  <p><%= source %></p>
<% } %>
<hr>
<h2>ファイル書き込み</h2>
<form action="<%= request.getContextPath() %>/ex1" method="post">
<p>ファイルに書き込む内容：<br>
<textarea rows="5" cols="40" name="text"></textarea>
</p>
<button type="submit">書き込む</button>
</form>
<% if (result != null) { %>
  <p><%= result %></p>
<% } %>
</body>
</html>