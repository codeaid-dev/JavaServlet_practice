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
<table width="100%">
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
      <%
      String todoDesc = t.get("description");
      String todoRes = "";
      for (int i=0; i<todoDesc.length(); i++) {
        char c = todoDesc.charAt(i);
        if (c == '\n') { todoRes += "<br>"; }
        else { todoRes += c; }
      }
      %>
      <p><%=todoRes%></p>
      <!-- 移動フォーム -->
      <form action="<%=request.getContextPath()%>/task/move" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <select name="to">
          <option value="TODO" selected>TODO</option>
          <option value="DOING">DOING</option>
          <option value="DONE">DONE</option>
        </select>
        <button type="submit">移動</button>
      </form>
      <!-- 編集リンク -->
      <a href="<%=request.getContextPath()%>/task/edit?id=<%=t.get("id")%>">編集</a>
      <!-- 削除フォーム -->
      <form action="<%=request.getContextPath()%>/task/delete" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <button type="submit">削除</button>
        <!-- <button type="submit" onclick="return confirm('削除しますか？');">削除</button> -->
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
      <%
      String doingDesc = t.get("description");
      String doingRes = "";
      for (int i=0; i<doingDesc.length(); i++) {
        char c = doingDesc.charAt(i);
        if (c == '\n') { doingRes += "<br>"; }
        else { doingRes += c; }
      }
      %>
      <p><%=doingRes%></p>
      <form action="<%=request.getContextPath()%>/task/move" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <select name="to">
          <option value="TODO">TODO</option>
          <option value="DOING" selected>DOING</option>
          <option value="DONE">DONE</option>
        </select>
        <button type="submit">移動</button>
      </form>
      <!-- 編集リンク -->
      <a href="<%=request.getContextPath()%>/task/edit?id=<%=t.get("id")%>">編集</a>
      <!-- 削除フォーム -->
      <form action="<%=request.getContextPath()%>/task/delete" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <button type="submit">削除</button>
        <!-- <button type="submit" onclick="return confirm('削除しますか？');">削除</button> -->
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
      <%
      String doneDesc = t.get("description");
      String doneRes = "";
      for (int i=0; i<doneDesc.length(); i++) {
        char c = doneDesc.charAt(i);
        if (c == '\n') { doneRes += "<br>"; }
        else { doneRes += c; }
      }
      %>
      <p><%=doneRes%></p>
      <form action="<%=request.getContextPath()%>/task/move" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <select name="to">
          <option value="TODO">TODO</option>
          <option value="DOING">DOING</option>
          <option value="DONE" selected>DONE</option>
        </select>
        <button type="submit">移動</button>
      </form>
      <!-- 編集リンク -->
      <a href="<%=request.getContextPath()%>/task/edit?id=<%=t.get("id")%>">編集</a>
      <!-- 削除フォーム -->
      <form action="<%=request.getContextPath()%>/task/delete" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%=t.get("id")%>">
        <button type="submit">削除</button>
        <!-- <button type="submit" onclick="return confirm('削除しますか？');">削除</button> -->
      </form>
    </div>
    <% } %>
  </td>
</tr>
</table>
</body>
</html>