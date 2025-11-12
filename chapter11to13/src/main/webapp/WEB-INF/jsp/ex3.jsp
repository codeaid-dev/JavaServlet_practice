<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>フィルタ・リスナー</title>
</head>
<body>
  <h2>フィルタ・リスナー</h2>
  <h3>許可するIPアドレス追加</h3>
  <form action="ex3allowip" method="post">
    <label>IPアドレス: <input type="text" name="ip"></label>
    <p><button type="submit">追加</button></p>
  </form>
</body>
</html>