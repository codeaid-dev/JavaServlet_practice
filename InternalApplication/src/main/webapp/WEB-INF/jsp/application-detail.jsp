<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Application" %>
<%@ page import="model.ApprovalRecord" %>
<%@ page import="java.util.List" %>

<%
  Application app =
      (Application) request.getAttribute("application");
  List<ApprovalRecord> approvalRecords =
      (List<ApprovalRecord>) request.getAttribute("approvalRecords");
  String role = (String) session.getAttribute("role");
  String error = (String) request.getAttribute("error");
  boolean canApprove =
      "APPROVER".equals(role)
      && app.getStatus()
          == model.ApplicationStatus.PENDING;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>申請詳細</title>
<style>
  body {
    font-family: sans-serif;
    margin: 30px;
  }

  table {
    border-collapse: collapse;
    width: 700px;
    max-width: 100%;
  }

  th, td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: left;
    vertical-align: top;
  }

  th {
    width: 150px;
    background-color: #f2f2f2;
  }

  .actions {
    margin-top: 20px;
  }

  textarea {
    width: 500px;
    max-width: 100%;
    height: 100px;
  }

  .error {
    color: #c00;
  }
</style>
</head>
<body>
<h1>申請詳細</h1>

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

<% if (error != null) { %>
  <p class="error"><%= escapeHtml(error) %></p>
<% } %>

<table>
  <tr>
    <th>申請番号</th>
    <td><%= app.getId() %></td>
  </tr>

  <tr>
    <th>申請者</th>
    <td><%= escapeHtml(app.getApplicantName()) %></td>
  </tr>

  <tr>
    <th>承認者</th>
    <td><%= escapeHtml(app.getApproverName()) %></td>
  </tr>

  <tr>
    <th>申請種別</th>
    <td><%= escapeHtml(app.getTypeName()) %></td>
  </tr>

  <tr>
    <th>件名</th>
    <td><%= escapeHtml(app.getTitle()) %></td>
  </tr>

  <tr>
    <th>申請理由</th>
    <td>
      <%= escapeHtml(app.getReason())
              .replace("\r\n", "\n")
              .replace("\r", "\n")
              .replace("\n", "<br>") %>
    </td>
  </tr>

  <tr>
    <th>金額</th>
    <td>
      <% if (app.getAmount() != null) { %>
          <%= String.format("%,d", app.getAmount()) %> 円
      <% } else { %>
          -
      <% } %>
    </td>
  </tr>

  <tr>
    <th>ステータス</th>
    <td>
      <%= escapeHtml(app.getStatus().getLabel()) %>
    </td>
  </tr>

  <tr>
    <th>申請日時</th>
    <td><%= escapeHtml(app.getAppliedAt()) %></td>
  </tr>

  <tr>
    <th>更新日時</th>
    <td><%= escapeHtml(app.getUpdatedAt()) %></td>
  </tr>
</table>

<% if (canApprove) { %>
  <h2>承認・却下</h2>
  <form action="<%= request.getContextPath() %>/applications/decision"
        method="post">
    <input type="hidden"
           name="id"
           value="<%= app.getId() %>">
    <div>
      <label for="comment">コメント・却下理由</label><br>
      <textarea id="comment"
                name="comment"
                maxlength="1000"
                placeholder="却下する場合は理由を入力してください。"></textarea>
    </div>
    <div class="actions">
      <button type="submit"
              name="action"
              value="APPROVED">
          承認
      </button>
      <button type="submit"
              name="action"
              value="REJECTED"
              onclick="return confirm('この申請を却下しますか？');">
          却下
      </button>
    </div>
  </form>
<% } %>

<%
  Integer loginUserId =
      (Integer) session.getAttribute("userId");

  boolean isApplicant =
      loginUserId != null
      && loginUserId == app.getApplicantId();

  boolean canWithdraw =
      isApplicant
      && "EMPLOYEE".equals(role)
      && app.getStatus() == model.ApplicationStatus.PENDING;
%>

<% if (canWithdraw) { %>
  <hr>
  <h2>申請の取り下げ</h2>
  <p>
    取り下げると、承認者はこの申請を承認できなくなります。
  </p>
  <form action="<%= request.getContextPath() %>/applications/withdraw"
        method="post"
        onsubmit="return confirm('この申請を取り下げますか？');">
    <input type="hidden"
           name="id"
           value="<%= app.getId() %>">
    <button type="submit">
        申請を取り下げる
    </button>
  </form>
<% } %>

<hr>

<h2>承認・却下履歴</h2>
<% if (approvalRecords == null || approvalRecords.isEmpty()) { %>
  <p>承認・却下の履歴はありません。</p>
<% } else { %>
  <table border="1">
    <thead>
      <tr>
        <th>日時</th>
        <th>操作した人</th>
        <th>操作</th>
        <th>コメント</th>
      </tr>
    </thead>
    <tbody>
    <% for (ApprovalRecord record : approvalRecords) { %>
      <tr>
        <td>
          <%= escapeHtml(record.getProcessedAt()) %>
        </td>
        <td>
          <%= escapeHtml(record.getApproverName()) %>
        </td>
        <td>
          <%= escapeHtml(record.getActionLabel()) %>
        </td>
        <td>
          <%= escapeHtml(record.getComment()) %>
        </td>
      </tr>
    <% } %>
    </tbody>
  </table>
<% } %>

<div class="actions">
  <a href="<%= request.getContextPath() %>/applications">
      申請一覧へ戻る
  </a>
</div>

</body>
</html>