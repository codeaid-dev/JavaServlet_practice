<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>簡易タイピング練習</title>
<style>
.text { font-size: 20px; }
</style>
</head>
<body>
  <h2>💻 簡易タイピング練習 💻️</h2>
  <p>${select.number}回目</p>
  <p class="text">${select.text}</p>
  <form method="post">
    <label>🔣入力: <input type="text" name="typing"></label>
    <p><button type="submit">送信</button></p>
  </form>
  <a href=""><button>はじめに戻る</button></a>
  <p>${result}</p>
</body>
</html>