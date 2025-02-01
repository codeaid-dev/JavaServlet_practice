<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習1</title>
</head>
<body>
  <h2>実習1</h2>
  <%
//    HttpSession sessionObj = request.getSession();
//    String user = (String) sessionObj.getAttribute("user");
    String user = (String) session.getAttribute("user"); //sessionは暗黙オブジェクト
    if (user == null) {
      response.sendRedirect("ex1.html");
      return;
    }
  %>
  <p>ログイン完了画面</p>
  <p>ようこそ、<%= user %> さん！</p>
  <a href="ex1logout">ログアウト</a>
</body>
</html>