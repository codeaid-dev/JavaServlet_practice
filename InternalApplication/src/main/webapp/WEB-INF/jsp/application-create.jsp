<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.ApplicationType" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規申請</title>
<style>
  body {
    font-family: sans-serif;
    margin: 30px;
  }

  .form-row {
    margin-bottom: 16px;
  }

  label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
  }

  input[type="text"],
  input[type="number"],
  textarea,
  select {
    box-sizing: border-box;
    width: 400px;
    max-width: 100%;
    padding: 8px;
  }

  textarea {
    height: 120px;
  }

  .error {
    color: #c00;
    margin-bottom: 15px;
  }
</style>
</head>
<body>
<h1>新規申請</h1>

<%
  String error = (String) request.getAttribute("error");

  List<ApplicationType> types =
      (List<ApplicationType>) request.getAttribute("types");
%>

<% if (error != null) { %>
  <p class="error"><%= escapeHtml(error) %></p>
<% } %>

<form action="<%= request.getContextPath() %>/applications/create"
      method="post">

  <div class="form-row">
    <label for="applicationTypeId">申請種別（必須）</label>

    <select id="applicationTypeId"
            name="applicationTypeId"
            required>

      <option value="">選択してください</option>

      <% if (types != null) {
          for (ApplicationType type : types) {
            String selected =
                String.valueOf(type.getId()).equals(
                    request.getParameter("applicationTypeId")
                ) ? "selected" : "";
      %>

      <option value="<%= type.getId() %>" <%= selected %>>
          <%= escapeHtml(type.getTypeName()) %>
      </option>

      <%  }
         }
      %>

    </select>
  </div>

  <div class="form-row">
    <label for="title">件名（必須）</label>

    <input type="text"
           id="title"
           name="title"
           maxlength="100"
           value="<%= escapeHtml(request.getParameter("title")) %>"
           required>
  </div>

  <div class="form-row">
    <label for="reason">申請理由（必須）</label>

    <textarea id="reason"
              name="reason"
              maxlength="2000"
              required><%= escapeHtml(request.getParameter("reason")) %></textarea>
  </div>

  <div class="form-row">
    <label for="amount">金額（円・任意）</label>

    <input type="number"
           id="amount"
           name="amount"
           min="0"
           step="1"
           value="<%= escapeHtml(request.getParameter("amount")) %>">
  </div>

  <button type="submit">申請する</button>

  <a href="<%= request.getContextPath() %>/applications"
     style="margin-left:15px;">
      戻る
  </a>

</form>

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
          .replace("'", "&#x27;");
  }
%>
</body>
</html>