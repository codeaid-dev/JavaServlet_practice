<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>動的インクルード</title>
</head>
<body>
  <jsp:include page="/WEB-INF/ex6header.html" />
  <h2>動的インクルード</h2>
  <h3>メインコンテンツ</h3>
  <p>ここはメインページの内容です。</p>
  <jsp:include page="/WEB-INF/ex6footer.html" />
</body>
</html>