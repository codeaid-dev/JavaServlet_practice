<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String bg = request.getParameter("bgcolor");
bg = (bg != null && bg.length() != 0) ? bg : "white";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サーブレットとJSP基本</title>
<style>
  #panel {
    width: 300px;
    border: solid 1px lightgray;
    padding: 20px;
    background: <%= bg %>
  }
</style>
</head>
<body>
  <h2>背景色変更</h2>
  <p>色を選択すると背景色が変わります。</p>
  <form action="ex14.jsp" method="post">
    <div id="panel">
      <label><input type="radio" name="bgcolor" value="white" <%= bg.equals("white") ? "checked" : "" %>>白色</label>
      <label><input type="radio" name="bgcolor" value="red" <%= bg.equals("red") ? "checked" : "" %>>赤色</label>
      <label><input type="radio" name="bgcolor" value="blue" <%= bg.equals("blue") ? "checked" : "" %>>青色</label>
      <label><input type="radio" name="bgcolor" value="yellow" <%= bg.equals("yellow") ? "checked" : "" %>>黄色</label>
      <label><input type="radio" name="bgcolor" value="green" <%= bg.equals("green") ? "checked" : "" %>>緑色</label>
    </div>
    <p><button type="submit">送信</button></p>
  </form>
</body>
</html>