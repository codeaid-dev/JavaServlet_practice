<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サーブレットとJSP基本</title>
</head>
<body>
  <h2>選択色でテキスト表示</h2>
  <form action="ex8" method="post">
    <label><input type="radio" name="color" value="red" checked>赤</label>
    <label><input type="radio" name="color" value="green">緑</label>
    <label><input type="radio" name="color" value="blue">青</label>
    <br>
    <label>メッセージ:<br>
    <input type="text" name="message"></label><br>
    <button type="submit">送信</button>
  </form>
</body>
</html>