<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学習記録登録</title>
</head>
<body>
<h1>学習記録登録</h1>

<form action="<%= request.getContextPath() %>/study/create"
      method="post">

  <p>科目：
    <input type="text" name="subject" required>
  </p>

  <p>学習日：
    <input type="date" name="studyDate" required>
  </p>

  <p>学習時間：
    <input type="number" name="minutes"
           min="1" required> 分
  </p>

  <p>学習内容：
    <textarea name="memo"></textarea>
  </p>

  <button type="submit">登録</button>

</form>

<p>
  <a href="<%= request.getContextPath() %>/study/list">一覧に戻る</a>
</p>
</body>
</html>