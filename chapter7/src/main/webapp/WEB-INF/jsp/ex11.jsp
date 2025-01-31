<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Favorite" %>
<%
Favorite favorite = (Favorite)request.getAttribute("favorite");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習11</title>
</head>
<body>
  <h2>実習11</h2>
  好きなスポーツ: <br>
  <ul>
  <% for (String s : favorite.getSports()) { %>
  <li><%= s %></li>
  <% } %>
  </ul>
  好きな選手: <br>
  <ul>
  <% for (String p : favorite.getPlayers()) { %>
  <li><%= p %></li>
  <% } %>
  </ul>
</body>
</html>