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
  <p>確認ページ</p>
  <%
  String name = (String)session.getAttribute("name");
  String address = (String)session.getAttribute("address");
  String product = request.getParameter("product");
  product = product != null ? product : "";
  session.setAttribute("product",product);
  %>
  <p>名前: <%= name %></p>
  <p>住所: <%= address %></p>
  <p>商品: <%= product %></p>
  <a href="ex5step1.jsp">修正する</a> | 
  <a href="ex5complete">注文を確定する</a>
</body>
</html>