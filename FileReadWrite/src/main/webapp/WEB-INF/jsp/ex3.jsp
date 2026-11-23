<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.nio.charset.StandardCharsets" %>

<%!
private String escapeHtml(String value) {
  return value
      .replace("&", "&amp;")
      .replace("<", "&lt;")
      .replace(">", "&gt;")
      .replace("\"", "&quot;")
      .replace("'", "&#39;");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メモ管理</title>
<style>
  body {
    font-family: sans-serif;
    margin: 30px;
  }
  textarea {
    width: 400px;
    height: 100px;
  }
  .memo {
    border: 1px solid #ccc;
    padding: 12px;
    margin: 12px 0;
  }
  .memo-body {
    white-space: pre-wrap;
  }
</style>
</head>
<body>
<h1>メモ管理</h1>
<h2>メモ登録</h2>
<form action="${pageContext.request.contextPath}/ex3"
      method="post">
  <input type="hidden" name="action" value="add">
  <p>
    タイトル<br>
    <input type="text" name="title" required>
  </p>
  <p>
    本文<br>
    <textarea name="body"
    placeholder="メモを入力してください" required></textarea>
  </p>
  <button type="submit">登録</button>
</form>
<hr>
<h2>メモ一覧</h2>
<%
List<String[]> memos =
    (List<String[]>) request.getAttribute("memos");
if (memos != null && !memos.isEmpty()) {
  for (String[] memo : memos) {
    %>
    <div class="memo">
      <h3>
        <%= escapeHtml(memo[1]) %>
      </h3>
      <p class="memo-body"><%= escapeHtml(memo[2]) %></p>
      <p>
        登録日時：
        <%= escapeHtml(memo[3]) %>
      </p>
      <form action="${pageContext.request.contextPath}/ex3"
            method="post">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="id"
               value="<%= escapeHtml(memo[0]) %>">
        <button type="submit">削除</button>
      </form>
    </div>
    <%
  }
} else {
  %>
  <p>メモは登録されていません。</p>
  <%
}
%>
</body>
</html>