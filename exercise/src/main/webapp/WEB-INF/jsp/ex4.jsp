<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習4</title>
</head>
<body>
  <h2>実習4</h2>
  <p>✊ じゃんけん5回戦 ✊</p>
  <form method="post">
    <p>
      <label style="margin-right: 10px;"><input type="radio" name="you" value="ぐー" checked>✊</label>
      <label style="margin-right: 10px;"><input type="radio" name="you" value="ちょき">✌️</label>
      <label><input type="radio" name="you" value="ぱー">✋</label>
    </p>
    <p><button type="submit" name="pon">ぽん</button></p>
  </form>
  <p><a href="/exercise/ex4"><button>新しい対戦</button></a></p>
  <%
  Map<Integer,String> history = (Map<Integer,String>)application.getAttribute("history");
  if (history != null) {
  %>
    <p>対戦結果</p>
    <% for (Map.Entry<Integer,String> entry : history.entrySet()) { %>
      <%= entry.getKey()+"回目："+entry.getValue() %><br>
    <% } %>
    <%
    String total = (String)request.getAttribute("total");
    if (total != null) {
    %>
      <p><%= total %></p>
    <% } %>
  <% } %>
</body>
</html>