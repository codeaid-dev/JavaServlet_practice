<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>動的インクルード</title>
</head>
<body>
  <h2>動的インクルード</h2>
  <h2>【選択したページの内容】</h2>
  <jsp:include page="<%= request.getParameter(\"page\") %>" />
</body>
</html>