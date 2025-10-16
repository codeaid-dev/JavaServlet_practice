<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Data" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>割り勘計算</title>
<style>
  table { border: 2px solid rgb(140 140 140); }
  th,td { border: 1px solid rgb(160 160 160); }
</style>
</head>
<body>
  <h2>割り勘計算</h2>
  <form method="post">
    <p>
    <label>支払総額：<input type="number" name="total" min="200" required>円</label><br>
    <label>人数：<input type="number" name="people" min="2" required></label>
    </p>
    <p>
    <label>端数処理：</label><br>
    <%
    String fraction = (String)request.getAttribute("fraction");
    if (fraction != null && fraction.equals("less")) {
    %>
      <label><input type="radio" name="fraction" value="more">1人が多く払う</label>
      <label><input type="radio" name="fraction" value="less" checked>1人が少なく払う</label>
      </p>
    <% } else { %>
      <label><input type="radio" name="fraction" value="more" checked>1人が多く払う</label>
      <label><input type="radio" name="fraction" value="less">1人が少なく払う</label>
      </p>
    <% } %>
    <p><button type="submit">計算</button></p>
    <%
    List<Data> result = (List<Data>)request.getAttribute("result");
    if (result != null) {
    %>
    <table>
      <tr>
        <th></th>
        <th>人数</th>
        <th>支払額(円)</th>
      </tr>
      <% for (Data data : result) { %>
      <tr>
        <td><%= data.getPerson() %></td>
        <td><%= data.getNumber() %></td>
        <td><%= data.getPrice() %></td>
      </tr>
      <% } %>
    </table>
    <% } %>
  </form>
</body>
</html>