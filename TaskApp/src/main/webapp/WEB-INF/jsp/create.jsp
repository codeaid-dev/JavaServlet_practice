<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク作成</title>
</head>
<body>
<h2>タスク作成</h2>
<form action="<%=request.getContextPath()%>/task/create" method="post">
  タイトル: <input type="text" name="title" required><br>
  説明: <br><textarea name="description" rows="4" cols="40"></textarea><br>
  期限: <input type="date" name="due_date"><br>
  状態:
  <select name="status">
    <option value="TODO">TODO</option>
    <option value="DOING">DOING</option>
    <option value="DONE">DONE</option>
  </select><br>
  <input type="submit" value="作成">
</form>
<a href="<%=request.getContextPath()%>/tasks">戻る</a>
</body>
</html>