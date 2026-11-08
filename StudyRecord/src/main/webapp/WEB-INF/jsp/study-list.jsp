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
</tr>

<% for (StudyRecord record : records) { %>
<tr>
  <td><%= record.getId() %></td>
  <td><%= record.getSubject() %></td>
  <td><%= record.getStudyDate() %></td>
  <td><%= record.getMinutes() %>分</td>
  <td><%= record.getMemo() == null
      ? "" : record.getMemo().replaceAll("\\R","<br>") %></td>
</tr>
<% } %>
</table>
</body>
</html>