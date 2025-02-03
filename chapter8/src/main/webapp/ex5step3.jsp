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
  <p>ステップ3：商品を入力
  <%
  String address = request.getParameter("address");
  address = address != null ? address : "";
  session.setAttribute("address",address);
  String product = (String)session.getAttribute("product");
  product = product != null ? product : "";
  %>
  <form action="ex5confirm.jsp" method="post">
  <label>商品: <input type="text" name="product" value=<%= product %>></label>
  <p><button type="submit">確認</button></p>
  </form>
  <a href="ex5step2.jsp">戻る</a>
</body>
</html>