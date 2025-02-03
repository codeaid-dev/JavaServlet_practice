<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習5</title>
</head>
<body>
  <h2>実習5</h2>
  <p>ステップ1：名前を入力</p>
  <%
  String name = (String)session.getAttribute("name");
  name = name != null ? name : "";
  %>
  <form action="ex5step2.jsp" method="post">
    <label>名前: <input type="text" name="name" value=<%= name %>></label>
    <p><button type="submit">次へ</button></p>
  </form>
</body>
</html>