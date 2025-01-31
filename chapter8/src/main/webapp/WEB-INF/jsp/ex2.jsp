<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習2</title>
</head>
<body>
  <h2>実習2</h2>
  <%
    HttpSession sessionObj = request.getSession();
    String user = (String) sessionObj.getAttribute("user");
    if (user == null) {
        response.sendRedirect("ex2");
        return;
    }
  %>
  <p>ようこそ、<%= user %> さん！</p>
  <a href="ex2logout">ログアウト</a>
</body>
</html>