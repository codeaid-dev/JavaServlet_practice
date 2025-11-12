<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL式・JSTL</title>
<style>
  table { border: 2px solid rgb(140 140 140); }
  th,td { border: 1px solid rgb(160 160 160); }
</style>
</head>
<body>
  <h2>EL式・JSTL</h2>
  <h3>商品リスト</h3>
  <table>
    <tr><th>商品名</th><th>価格</th><th>コメント</th></tr>
    <c:forEach var="product" items="${products}">
      <tr>
        <td>${product.name}</td>
        <td>${product.price} 円</td>
        <c:choose>
          <c:when test="${product.price > 100}">
            <td>高めの商品</td>
          </c:when>
          <c:otherwise>
            <td>普通の商品</td>
          </c:otherwise>
        </c:choose>
      </tr>
    </c:forEach>
  </table>
</body>
</html>