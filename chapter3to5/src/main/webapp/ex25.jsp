<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String strLeft = request.getParameter("left");
String strRight = request.getParameter("right");
String operator = request.getParameter("operator");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サーブレットとJSP基本</title>
</head>
<body>
  <h2>四則演算</h2>
  <p>計算式:</p>
  <form action="ex25.jsp" method="post">
    <input type="number" name="left" style="width:55px" value=<%= (strLeft != null) ? strLeft : "" %> required>
    <select name="operator">
      <option value="+" <%= (operator == null || (operator != null && operator.equals("+"))) ? "selected" : "" %>>+</option>
      <option value="-" <%= (operator != null && operator.equals("-")) ? "selected" : "" %>>-</option>
      <option value="*" <%= (operator != null && operator.equals("*")) ? "selected" : "" %>>*</option>
      <option value="/" <%= (operator != null && operator.equals("/")) ? "selected" : "" %>>/</option>
    </select>
    <input type="number" name="right" style="width:55px" value=<%= (strRight != null) ? strRight : "" %> required>
    <p><button type="submit">計算</button></p>
  </form>
  <%
  int result = 0;
  String error = "";
  if (strLeft != null && strRight != null && operator != null) {
    int left = Integer.parseInt(strLeft);
    int right = Integer.parseInt(strRight);
    if (operator.equals("+")) {
      result = left + right;
    } else if (operator.equals("-")) {
      result = left - right;
    } else if (operator.equals("*")) {
      result = left * right;
    } else {
      if (operator.equals("/") && right != 0) {
        result = left / right;
      } else {
        error = "0で割ることはできません。";
      }
    }
  %>
	  <% if (error.length() != 0) { %>
	    <%= error %>
	  <% } else { %>
	    <p>結果: <%= result %></p>
	  <% } %>
  <% } %>
</body>
</html>