<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>静的インクルード</title>
</head>
<body>
<%@ include file="WEB-INF/jsp/ex5header.jsp" %>
  <h2>静的インクルード</h2>
  <h3>メインコンテンツ</h3>
  <p>ここはメインページの内容です。</p>
<%@ include file="WEB-INF/jsp/ex5footer.jsp" %>
</body>
</html>