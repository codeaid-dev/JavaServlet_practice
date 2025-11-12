package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex2")
public class Ex2 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    String itemId = request.getParameter("id");
    Map<String, String> cache = (Map<String, String>) application.getAttribute("products");
    String item = cache.getOrDefault(itemId, "商品が見つかりません");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>リスナー</title>
        </head>
        <body>
        <h2>リスナー</h2>
        """);
    out.println("<p>検索結果: 商品ID " + itemId + " -> " + item + "</p>");
    out.println("""
        <a href="ex2.html">トップ</a>
        </body>
        </html>
        """);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String itemId = request.getParameter("id");
    String itemName = request.getParameter("name");
    ServletContext application = getServletContext();
    Map<String, String> cache = (Map<String, String>) application.getAttribute("products");

    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>リスナー</title>
        </head>
        <body>
        <h2>リスナー</h2>
        """);

    if (itemId == null || itemName == null) {
      out.println("<p>エラー: 商品IDと商品名の両方を指定してください。</p>");
    } else {
      cache.put(itemId, itemName);
      out.println("<p>商品追加: 商品ID " + itemId + " -> " + itemName + "</p>");
    }

    out.println("""
        <a href="ex2.html">トップ</a>
        </body>
        </html>
        """);
  }

}
