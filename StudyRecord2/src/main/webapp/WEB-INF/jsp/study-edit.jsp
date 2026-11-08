<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.StudyRecord" %>
<%
  StudyRecord record =
      (StudyRecord) request.getAttribute("record");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学習記録編集</title>
</head>
<body>
<h1>学習記録編集</h1>

<form action="<%= request.getContextPath() %>/study/update" method="post">
  <input type="hidden" name="id" value="<%= record.getId() %>">
  <p>科目：
    <input type="text"
           name="subject"
           value="<%= record.getSubject() %>"
           required>
  </p>
  <p>学習日：
    <input type="date"
           name="studyDate"
           value="<%= record.getStudyDate() %>"
           required>
  </p>
  <p>学習時間：
    <input type="number"
           name="minutes"
           min="1"
           value="<%= record.getMinutes() %>"
           required> 分
  </p>
  <p>
    学習内容：
    <textarea name="memo"><%= record.getMemo() == null
        ? "" : record.getMemo() %></textarea>
  </p>
  <button type="submit">更新</button>
</form>
<p>
  <a href="<%= request.getContextPath() %>/study/list">一覧に戻る</a>
</p>
</body>
</html>