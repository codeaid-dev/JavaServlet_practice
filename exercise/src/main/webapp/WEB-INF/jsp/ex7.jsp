<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習7</title>
<style>
.text { font-size: 20px; }
</style>
</head>
<body>
  <h2>実習7</h2>
  <h3>💻 簡易タイピング練習 💻️</h3>
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