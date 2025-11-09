<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サーブレットとJSP基本</title>
</head>
<body>
  <h2>POST送信 & 受信</h2>
  <form action="ex7" method="post">
    <label>番号:<br>
    <input type="text" name="id"></label><br>
    <label>名前:<br>
    <input type="text" name="name"></label><br>
    <button type="submit">送信</button>
  </form>
</body>
</html>