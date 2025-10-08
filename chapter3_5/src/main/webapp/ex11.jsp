<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap, java.util.Map" %>
<%
Map<String,String> people = new HashMap<>(Map.of("101","高橋","102","鈴木","103","後藤","104","佐藤","105","田中"));
String method = request.getMethod();
String id = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習11</title>
</head>
<body>
  <h2>実習11</h2>
  <form action="ex11.jsp" method="post">
    <label>ID: <input type="text" name="id"></label>
    <button type="submit">送信</button>
  </form>
  <%
  if ("POST".equalsIgnoreCase(method)) {
    if (id != null && id.length() != 0) {
      String result = people.get(id);
      if (result != null) {
        %>
        <p><%= result %></p>
        <%
      } else {
        %>
        <p>該当なし</p>
        <%
      }
    } else {
      %>
      <p>該当なし</p>
      <%
    }
  }
  %>
</body>
</html>