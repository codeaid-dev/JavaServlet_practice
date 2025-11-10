<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%
List<String> cart = (List<String>) session.getAttribute("cart");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>セッションスコープ</title>
</head>
<body>
  <h2>セッションスコープ</h2>
  <p>カートの中身</p>
  <% if (cart != null) { %>
    <ul>
      <% for (String item : cart) { %>
        <li><%= item %></li>
      <% } %>
    </ul>
  <% } %>
  <a href="ex4.html">商品を追加</a> | 
  <a href="ex4remove">商品を削除</a>
</body>
</html>