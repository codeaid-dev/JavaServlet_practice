<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.Map" %>
<%
  Map<String,String> user = (Map<String,String>)session.getAttribute("user");
  if (user == null) { response.sendRedirect(request.getContextPath()+"/login"); return; }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約一覧</title>
<style>
table,th,td { border: 1px solid; }
th,td { padding: 5px; }
</style>
</head>
<body>
<h2>予約一覧</h2>
ログイン： <b><%=user.get("username")%></b> （<%=user.get("role")%>） ｜ <a href="<%=request.getContextPath()%>/logout">ログアウト</a><br><br>
<a href="<%=request.getContextPath()%>/create">新規予約</a><br><br>

<table>
<tr><th>ID</th><th>件名</th><th>日付</th><th>時間</th><th>メモ</th><th>登録者</th><th>権限</th></tr>
<%
  List<Map<String,String>> list = (List<Map<String,String>>)request.getAttribute("list");
  if (list != null) {
    for (Map<String,String> r : list) {
      boolean editable = "admin".equals(user.get("role")) || user.get("username").equals(r.get("username"));
%>
<tr>
  <td><%=r.get("id")%></td>
  <td><%=r.get("name")%></td>
  <td><%=r.get("date")%></td>
  <td><%=r.get("time")%></td>
  <td><%=r.get("note")%></td>
  <td><%=r.get("username")%></td>
  <td>
    <% if (editable) { %>
      <a href="<%=request.getContextPath()%>/edit?id=<%=r.get("id")%>">編集</a>
    <% } %>
    <% if ("admin".equals(user.get("role"))) { %>
      &nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/delete?id=<%=r.get("id")%>">削除</a>
    <% } %>
  </td>
</tr>
<%
    }
  }
%>
</table>
</body>
</html>