<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Review" %>
<%
  ServletContext app = application;
  List<Review> list = (List<Review>) app.getAttribute("reviewList");
  if (list == null) list = new ArrayList<>();
  String user = (String) session.getAttribute("user");
  if (user == null) {
    response.sendRedirect("/");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品レビュー投稿</title>
<style>
body { font-family: sans-serif; margin: 20px; }
form { margin-bottom: 20px; }
textarea { width: 300px; height: 60px; }
.review {
  border: 1px solid #ccc; padding: 10px; margin: 5px 0; border-radius: 8px;
}
small { color: gray; }
#top { margin: 0 5px; }
</style>
</head>
<body>
<h2>商品レビュー投稿</h2>
<h3>ようこそ、<%= user %> さん</h3>
<form action="review" method="post">
  商品名：<input type="text" name="product"><br>
  レビュー：<br>
  <textarea name="comment"></textarea><br>
  <button type="submit">投稿</button>
  <a href="<%= request.getContextPath() %>" id="top">TOPへ</a>
</form>

<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
    <p style="color:red;"><%= error %></p>
<% } %>

<hr>
<h3>投稿一覧</h3>
<% if (list.isEmpty()) { %>
  <p>まだレビューはありません。</p>
<% } else { 
//    synchronized(list) { // リストへの同時アクセスを制御
      for (int i = list.size() - 1; i >= 0; i--) {
        Review r = list.get(i);
%>
        <div class="review">
          <strong><%= r.getProduct() %></strong><br>
          <% String[] comment = r.getComment().split("\n");
          for (String str : comment) { %>
            <%= str %><br>
          <% } %>
          <small>投稿者：<%= r.getUser() %> / <%= r.getDate() %></small>
        </div>
<%
      }
//    }
} %>
</body>
</html>