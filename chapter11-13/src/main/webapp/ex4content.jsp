<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習4</title>
</head>
<body>
  <h2>実習4</h2>
  <h2>【選択したページの内容】</h2>
  <jsp:include page="<%= request.getParameter(\"page\") %>" />
</body>
</html>