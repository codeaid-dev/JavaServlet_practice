<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%
Map<String,String> zipdata = (Map<String,String>)application.getAttribute("zipdata");
String zip1="",zip2="",city="",place="";
if (zipdata != null) {
  zip1 = zipdata.get("zip1");
  zip1 = zip1 != null ? zip1 : "";
  zip2 = zipdata.get("zip2");
  zip2 = zip2 != null ? zip2 : "";
  city = zipdata.get("city");
  city = city != null ? city : "";
  place = zipdata.get("place");
  place = place != null ? place : "";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習5</title>
</head>
<body>
  <h2>実習5</h2>
  <h3>大阪府 - 郵便番号検索</h3>
  <form method="POST">
    <p><label>郵便番号：<input type="text" name="zip1" size="3" value=<%= zip1 %>> - 
    <input type="text" name="zip2" size="4" value=<%= zip2 %>></label>
    <button type="submit" name="ziptocity">検索</button></p>
  </form>
  <hr style="width:100px; margin:0;">
  <form method="POST">
    <p><label>市区町村名：<input type="text" name="city" value=<%= city %>></label><br>
    <p><label>地名：<input type="text" name="place" value=<%= place %>></label>
    <button type="submit" name="citytozip">検索</button></p>
  </form>
</body>
</html>