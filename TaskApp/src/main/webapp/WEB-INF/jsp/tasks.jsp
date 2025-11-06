<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク管理</title>
</head>
<body>
<h2>タスク管理</h2>
<a href="<%=request.getContextPath()%>/task/create">新規作成</a>
<table border="0" width="100%">
<tr>
  <!-- TODO column -->
  <td valign="top" width="33%">
    <h3>TODO</h3>
    <%
      List<Map<String,String>> todos = (List<Map<String,String>>)request.getAttribute("todos");
      for (Map<String,String> t : todos) {
    %>
    <div style="border:1px solid #ccc; padding:6px; margin:6px;">
      <b><%=t.get("title")%></b><br>
      <small>期限: <%=t.get("due_date")%></small><br>
      <p><%=t.get("description")%></p>
      <!-- 移動フォーム -->
      <form action="<%=request.getContextPath()%>/task/move" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <select name="to">
          <option value="TODO" selected>TODO</option>
          <option value="DOING">DOING</option>
          <option value="DONE">DONE</option>
        </select>
        <input type="submit" value="移動">
      </form>
      <a href="<%=request.getContextPath()%>/task/edit?id=<%=t.get("id")%>">編集</a>
      <form action="<%=request.getContextPath()%>/task/delete" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <input type="submit" value="削除" onclick="return confirm('削除しますか？');">
      </form>
    </div>
    <% } %>
  </td>

  <!-- DOING column -->
  <td valign="top" width="33%">
    <h3>DOING</h3>
    <%
      List<Map<String,String>> doing = (List<Map<String,String>>)request.getAttribute("doing");
      for (Map<String,String> t : doing) {
    %>
    <div style="border:1px solid #ccc; padding:6px; margin:6px;">
      <b><%=t.get("title")%></b><br>
      <small>期限: <%=t.get("due_date")%></small><br>
      <p><%=t.get("description")%></p>
      <form action="<%=request.getContextPath()%>/task/move" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <select name="to">
          <option value="TODO">TODO</option>
          <option value="DOING" selected>DOING</option>
          <option value="DONE">DONE</option>
        </select>
        <input type="submit" value="移動">
      </form>
      <a href="<%=request.getContextPath()%>/task/edit?id=<%=t.get("id")%>">編集</a>
      <form action="<%=request.getContextPath()%>/task/delete" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <input type="submit" value="削除" onclick="return confirm('削除しますか？');">
      </form>
    </div>
    <% } %>
  </td>

  <!-- DONE column -->
  <td valign="top" width="34%">
    <h3>DONE</h3>
    <%
      List<Map<String,String>> done = (List<Map<String,String>>)request.getAttribute("done");
      for (Map<String,String> t : done) {
    %>
    <div style="border:1px solid #ccc; padding:6px; margin:6px;">
      <b><%=t.get("title")%></b><br>
      <small>期限: <%=t.get("due_date")%></small><br>
      <p><%=t.get("description")%></p>
      <form action="<%=request.getContextPath()%>/task/move" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <select name="to">
          <option value="TODO">TODO</option>
          <option value="DOING">DOING</option>
          <option value="DONE" selected>DONE</option>
        </select>
        <input type="submit" value="移動">
      </form>
      <a href="<%=request.getContextPath()%>/task/edit?id=<%=t.get("id")%>">編集</a>
      <form action="<%=request.getContextPath()%>/task/delete" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <input type="submit" value="削除" onclick="return confirm('削除しますか？');">
      </form>
    </div>
    <% } %>
  </td>
</tr>
</table>
</body>
</html>