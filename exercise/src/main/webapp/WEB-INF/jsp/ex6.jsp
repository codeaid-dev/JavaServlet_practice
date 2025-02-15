<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%
StringBuilder question = (StringBuilder)application.getAttribute("question");
List<Character> correct = (List<Character>)application.getAttribute("correct");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習6</title>
</head>
<body>
  <h2>実習6</h2>
  <h3>❓ アルファベットクイズ ❓</h3>
  <p>抜けているアルファベットはどれ？(<%= correct.size() %>文字抜けている)</p>
  <p style="font-size:20px;"><%= question.toString() %></p>
  <form method="post">
    <label>答え: <input type="text" name="answer"></label>
    <p><button type="submit">送信</button></p>
  </form>
  <%
  String result = (String)request.getAttribute("result");
  if (result != null) {
  %>
  <h3>結果 ‼️</h3>
  <p><%= result %></p>
  <% } %>
  <a href="">トップ</a>
</body>
</html>