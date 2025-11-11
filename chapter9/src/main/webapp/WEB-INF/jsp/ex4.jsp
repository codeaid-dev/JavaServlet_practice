<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アプリケーションスコープ</title>
  <style>
    .slot { font-size: 24px; font-weight: bold; letter-spacing: 10px; }
    .history { font-size: 18px; }
  </style>
</head>
<body>
  <h2>アプリケーションスコープ</h2>
  <h3>🎰 文字スロットマシン 🎰</h3>

  <form action="ex4" method="post">
    <p><button type="submit">スロットを回す！</button></p>
  </form>

  <h3>📍 履歴</h3>
  <ul class="history">
    <%
      List<String> history = (List<String>) application.getAttribute("history");
      if (history != null) {
        for (String result : history) {
    %>
      <li class="slot"><%= result %></li>
    <% 
        }
      } else {
      %>
        <p>履歴はありません。</p>
      <% 
      }
    %>
  </ul>
</body>
</html>