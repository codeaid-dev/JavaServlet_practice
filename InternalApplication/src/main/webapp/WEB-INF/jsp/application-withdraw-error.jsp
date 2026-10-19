<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>申請の取り下げ</title>
</head>
<body>
  <h1>申請の取り下げ</h1>
  <p>
    <%= escapeHtml(
        (String) request.getAttribute("error")
    ) %>
  </p>

  <p>
    <a href="<%= request.getContextPath() %>/applications">
        申請一覧に戻る
    </a>
  </p>

  <%!
    private String escapeHtml(String value) {
      if (value == null) {
        return "";
      }

      return value
          .replace("&", "&amp;")
          .replace("<", "&lt;")
          .replace(">", "&gt;")
          .replace("\"", "&quot;")
          .replace("'", "&#39;");
    }
  %>
</body>
</html>