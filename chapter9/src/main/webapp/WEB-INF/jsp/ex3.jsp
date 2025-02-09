<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実習3</title>
</head>
<body>
  <h2>実習3</h2>
  <%
  Map<Integer, Integer> pageViews = (Map<Integer, Integer>) application.getAttribute("pageViews");
  if (pageViews != null) {
    String error = (String)request.getAttribute("error");
    if (error == null) {
      Integer currentPage = (Integer)request.getAttribute("currentPage");
      out.println("<h3>ページ"+currentPage+"の画面</h3>");
      for (Map.Entry<Integer, Integer> entry : pageViews.entrySet()) {
        if (entry.getKey().equals(currentPage)) {
          out.println("<p>ページ"+currentPage+"の訪問回数："+entry.getValue()+"</p>");
        }
      }
      for (int i=1; i<6; i++) {
        if (currentPage != i) {
          out.println("<span style='margin: 0 5px;'>");
          out.println("<a href='/chapter9/ex3?page="+i+"'>"+i+"</a>");
          out.println("</span>");
        } else {
          out.println("<span style='margin: 0 5px;'>"+i+"</span>");
        }
      }
    } else {
      out.println("<p>"+error+"</p>");
    }
  } else {
    out.println("<p>訪問履歴はありません。</p>");
  }
  %>
</body>
</html>