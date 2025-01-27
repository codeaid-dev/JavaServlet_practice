<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Product,java.util.List" %>
<%
List<Product> productList = (List<Product>)request.getAttribute("products");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習7</title>
<style>
table, th, td {
  border-collapse: collapse;
  border: 1px solid;
}
</style>
</head>
<body>
  <h2>実習7</h2>
  <table>
  <tr>
  <th>製品名</th>
  <th>価格</th>
  </tr>
  <% for (Product product : productList) { %>
  <tr>
  <td><%= product.getName() %></td>
  <td><%= product.getPrice() %></td>
  </tr>
  <% } %>
  </table>
</body>
</html>