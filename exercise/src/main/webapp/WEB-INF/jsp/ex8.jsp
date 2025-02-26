<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Product" %>
<%
List<Product> products = (List<Product>)application.getAttribute("products");
List<Product> cart = (List<Product>)session.getAttribute("cart");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習8</title>
</head>
<body>
  <h2>実習8</h2>
  <h3>商品一覧</h3>
  <form method="post">
  <% for (Product product : products) { %>
    <label>
    <input type="checkbox" name="option" value="<%= product.getName() %>"><%= product.getName() %>（<%= product.getPrice() %>円）
    </label>
    <label><input type="number" name="<%= "quantity"+product.getName() %>" min="1" max="10" value="1">個</label><br>
  <% } %>
  <p><button type="submit" name="add_to_cart">カートに追加</button></p>
  </form>
  
  <h3>カート</h3>
  <% if (cart != null && cart.size() != 0) {
    int total = 0;
    for (Product product : cart) { %>
      <%= product.getName() %>（<%= product.getPrice() %>円） x <%= product.getQuantity() %>個<br>
      <% total += product.getPrice() * product.getQuantity();
    } %>
    合計：<%= (int)(total*1.1) %>円(税込)<br>
    <form method="post">
      <button type="submit" name="del_from_cart">カートを空にする</button>
    </form>
  <% } else { %>
    <p>カートに商品はありません。</p>
  <% } %>
</body>
</html>