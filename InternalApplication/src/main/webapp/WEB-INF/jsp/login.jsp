<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社内申請管理システム - ログイン</title>
</head>
<body>
<h1>社内申請管理システム</h1>
<h2>ログイン</h2>

<%
  String error = (String) request.getAttribute("error");

  if (error != null) {
%>
  <p style="color: red;">
    <%= error %>
  </p>
<%
  }
%>

<form method="post"
  action="<%= request.getContextPath() %>/login">

  <div>
    <label for="username">ユーザーID</label>
    <input type="text"
           id="username"
           name="username"
           required>
  </div>

  <div>
    <label for="password">パスワード</label>
    <input type="password"
           id="password"
           name="password"
           required>
  </div>

  <button type="submit">ログイン</button>
</form>
</body>
</html>