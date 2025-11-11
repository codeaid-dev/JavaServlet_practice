<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アプリケーションスコープ</title>
</head>
<body>
  <h2>アプリケーションスコープ</h2>
  <p>メッセージ</p>
  <ul>
  <% 
  List<String> messages = (List<String>) application.getAttribute("messages");
  if (messages != null) {
      for (String message : messages) {
        message = message.replaceAll("\n", "<br>");
  %>
          <li><%= message %></li>
  <% 
      }
  } else { 
  %>
      <p>投稿はまだありません。</p>
  <% } %>
  </ul>
  
  <form action="ex2" method="post">
     <textarea name="message" rows="5" cols="30"></textarea>
     <p><button type="submit">投稿</button></p>
  </form>
</body>
</html>