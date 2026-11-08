<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.StudyRecord" %>
<%
  List<StudyRecord> records =
      (List<StudyRecord>) request.getAttribute("records");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学習記録一覧</title>
</head>
<body>
<h1>学習記録一覧</h1>
<p>
  <a href="<%= request.getContextPath() %>/study/create">
      新規登録
  </a>
</p>

<table border="1">
<tr>
  <th>ID</th>
  <th>科目</th>
  <th>学習日</th>
  <th>学習時間</th>
  <th>学習内容</th>
  <th>操作</th>
</tr>

<% for (StudyRecord record : records) { %>
<tr>
  <td><%= record.getId() %></td>
  <td><%= record.getSubject() %></td>
  <td><%= record.getStudyDate() %></td>
  <td><%= record.getMinutes() %>分</td>
  <td><%= record.getMemo() == null
      ? "" : record.getMemo().replaceAll("\\R","<br>") %></td>
  <td>
    <!-- 編集 -->
    <a href="<%= request.getContextPath() %>/study/edit?id=<%= record.getId() %>">
        編集
    </a>
    <!-- 削除 -->
    <form action="<%= request.getContextPath() %>/study/delete"
          method="post"
          style="display:inline"
          onsubmit="return confirm('削除してよろしいですか？');">
      <input type="hidden"
             name="id"
             value="<%= record.getId() %>">
      <button type="submit">削除</button>
    </form>
  </td>
</tr>
<% } %>
</table>
</body>
</html>