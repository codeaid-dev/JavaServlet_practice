<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規予約</title>
</head>
<body>
<h2>新規予約</h2>
<p style="color:red">${requestScope.error}</p>
<form method="post" action="${pageContext.request.contextPath}/create">
  <label>件名：<input type="text" name="name" required></label><br>
  <label>日付：<input type="date" name="date" required></label><br>
  <label>時間：<input type="time" name="time" required></label><br>
  <label>メモ：<input type="text" name="note"></label><br>
  <p><button type="submit">登録</button></p>
</form>
<a href="${pageContext.request.contextPath}/list">戻る</a>
</body>
</html>