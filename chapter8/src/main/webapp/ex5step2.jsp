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
  <p>ステップ2：住所を入力</p>
  <%
  String name = request.getParameter("name");
  name = name != null ? name : "";
  session.setAttribute("name",name);
  String address = (String)session.getAttribute("address");
  address = address != null ? address : "";
  %>
  <form action="ex5step3.jsp" method="post">
  <label>住所: <input type="text" name="address" value=<%= address %>></label>
  <p><button type="submit">次へ</button></p>
  </form>
  <a href="ex5step1.jsp">戻る</a>
</body>
</html>