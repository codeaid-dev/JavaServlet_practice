<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Application" %>
<%@ page import="model.ApplicationStatus" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>申請一覧</title>
  <style>
    body {
      font-family: sans-serif;
      margin: 30px;
    }

    table {
      border-collapse: collapse;
      width: 100%;
      margin-top: 20px;
    }

    th, td {
      border: 1px solid #ccc;
      padding: 8px;
      text-align: left;
    }

    th {
      background-color: #f2f2f2;
    }

    .actions {
      margin-bottom: 15px;
    }
  </style>
</head>
<body>
<h1>申請一覧</h1>

<p>
  ログインユーザー：
  <%= escapeHtml((String) session.getAttribute("displayName")) %>
</p>

<div class="actions">
  <% if ("EMPLOYEE".equals(session.getAttribute("role"))) { %>
    <a href="<%= request.getContextPath() %>/applications/create">
      新規申請
    </a>
  <% } %>

  <form action="<%= request.getContextPath() %>/logout"
        method="post"
        style="display:inline; margin-left:20px;">
    <button type="submit">ログアウト</button>
  </form>
</div>

<%
  List<Application> applications =
      (List<Application>) request.getAttribute("applications");
%>

<% if (applications == null || applications.isEmpty()) { %>
  <p>申請はありません。</p>
<% } else { %>

<table>
  <thead>
    <tr>
      <th>申請番号</th>
      <th>申請種別</th>
      <th>件名</th>
      <th>申請者</th>
      <th>承認者</th>
      <th>金額</th>
      <th>ステータス</th>
      <th>申請日時</th>
    </tr>
  </thead>

  <tbody>
    <% for (Application app : applications) { %>
      <tr>
        <td>
          <a href="<%= request.getContextPath() %>/applications/detail?id=<%= app.getId() %>">
            <%= app.getId() %>
          </a>
        </td>
        <td><%= escapeHtml(app.getTypeName()) %></td>
        <td><%= escapeHtml(app.getTitle()) %></td>
        <td><%= escapeHtml(app.getApplicantName()) %></td>
        <td><%= escapeHtml(app.getApproverName()) %></td>
        <td>
          <% if (app.getAmount() != null) { %>
            <%= String.format("%,d", app.getAmount()) %> 円
          <% } else { %>
            -
          <% } %>
        </td>
        <td>
          <%= escapeHtml(app.getStatus().getLabel()) %>
        </td>
        <td><%= escapeHtml(app.getAppliedAt()) %></td>
      </tr>
    <% } %>
  </tbody>
</table>

<% } %>

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