<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h2>ログイン</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
  <label>ユーザー名：<input name="username"></label><br>
  <label>パスワード：<input type="password" name="password"></label><br>
  <p><button type="submit">ログイン</button></p>
</form>
<p style="color: red;">${requestScope.error}</p>
</body>
</html>