<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
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
  li {
    margin-bottom: 10px;
  }
  .error {
    color: red;
  }
  .success {
    color: green;
  }
</style>
</head>
<body>
<h1>メモ登録</h1>

<form action="<%= request.getContextPath() %>/ex2"
      method="post">
  <textarea name="memo"
      placeholder="メモを入力してください"></textarea>
  <br><br>
  <button type="submit">保存</button>
</form>

<%
String message = (String) request.getAttribute("message");
String messageType = (String) request.getAttribute("messageType");

if (message != null) {
%>
  <p class="<%= messageType %>">
      <%= message %>
  </p>
<%
}
%>
<hr>
<h2>メモ内容</h2>
<%
List<String> memoList =
    (List<String>) request.getAttribute("memoList");

if (memoList != null && !memoList.isEmpty()) {
%>
  <p>
  <%
  for (String memo : memoList) {
  %>
    <%= memo %>
    <br>
  <%
  }
  %>
  </p>
<%
} else {
%>
    <p>登録されたメモはありません。</p>
<%
}
%>
</body>
</html>