<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社内掲示板</title>
</head>
<body>
  <h2>📋 社内掲示板</h2>

  <form action="add" method="post">
    名前：<input type="text" name="name" required><br>
    投稿：<br>
    <textarea name="message" rows="4" cols="50" required></textarea><br>
    <button type="submit">投稿</button>
  </form>

  <hr>
  <h3>投稿一覧</h3>
  <%
    java.util.List<model.Post> posts = (java.util.List<model.Post>)request.getAttribute("posts");
    if (posts != null && !posts.isEmpty()) {
      for (model.Post p : posts) {
  %>
      <div>
        <strong><%= p.getName() %></strong>（<%= p.getCreatedAt() %>）<br>
        <pre><%= p.getMessage() %></pre>
        <hr>
      </div>
  <%
      }
    } else {
  %>
      <p>投稿はまだありません。</p>
  <%
    }
  %>
</body>
</html>