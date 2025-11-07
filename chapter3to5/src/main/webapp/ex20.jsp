<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String[] pref = {"北海道","青森県","岩手県","宮城県","秋田県","山形県","福島県","茨城県","栃木県","群馬県","埼玉県","千葉県","東京都","神奈川県"};
String[] city = {"札幌市","青森市","盛岡市","仙台市","秋田市","山形市","福島市","水戸市","宇都宮市","前橋市","さいたま市","千葉市","新宿区","横浜市"};
int index = (int)(Math.random()*pref.length);
String question = pref[index];
String correct = city[index];
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習20</title>
</head>
<body>
  <h2>実習20</h2>
  <p><%= question %>の県庁所在地は？</p>
  <form action="ex20" method="post">
  <label>答え: <input type="text" name="answer"></label><br>
  <input type="hidden" name="question" value="<%= question %>">
  <input type="hidden" name="correct" value="<%= correct %>">
  <p><button type="submit">送信</button></p>
  </form>
</body>
</html>