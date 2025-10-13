package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DBUtil;

@WebServlet("/")
public class SQLiteSample extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    ServletContext context = getServletContext();
    String realPath = context.getRealPath("/WEB-INF/db/sample.db");
    DBUtil.init(realPath);

    DBUtil.createTable();
    DBUtil.insertData();

    out.println("<h2>テーブル作成＆初期データ登録完了</h2>");

    out.println("<h2>スコア70以上のユーザー一覧</h2>");
    out.println("<style>table, th, td { border: 1px solid; }</style>");
    out.println("<table><tr><th>ID</th><th>Name</th><th>Score</th></tr>");

    for (var user : DBUtil.selectData()) {
      out.printf("<tr><td>%d</td><td>%s</td><td>%d</td></tr>",
          user.id, user.name, user.score);
    }

    out.println("</table>");

    DBUtil.deleteAll();

    out.println("<h2>テーブルを削除しました</h2>");
  }

}
