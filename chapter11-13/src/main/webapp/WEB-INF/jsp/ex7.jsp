<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- アクションタグを使ってスコープのJavaBeanを取得する(スコープにあれば取得、なければ作成する) --%>
<%-- <jsp:useBean id="user" class="model.User" scope="request"/> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習7</title>
</head>
<body>
  <h2>実習7</h2>
  <h3>EL式での表示</h3>
  <p>
  名前: ${user.name}<br>
  年齢: ${user.age}
  </p>
<%--
  <h3>アクションタグでの表示</h3>
  <p>
  名前: <jsp:getProperty name="user" property="name" /><br>
  年齢: <jsp:getProperty name="user" property="age" />
  </p>
--%>
  <a href="ex7.html">戻る</a>
</body>
</html>