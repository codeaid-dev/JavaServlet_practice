package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex1")
public class Ex1 extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private Map<String, String> cache;

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    cache = new HashMap<>();
    cache.put("1", "商品A");
    cache.put("2", "商品B");
    cache.put("3", "商品C");
  }

  public void destroy() {
    cache.clear();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String itemId = request.getParameter("id");
    String item = cache.getOrDefault(itemId, "商品が見つかりません");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>init() & destroy()</title>
        </head>
        <body>
        <h2>init() & destroy()</h2>
        """);
    out.println("<p>検索結果: 商品ID " + itemId + " -> " + item + "</p>");
    out.println("""
        <a href="ex1.html">トップ</a>
        </body>
        </html>
        """);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String itemId = request.getParameter("id");
    String itemName = request.getParameter("name");

    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>init() & destroy()</title>
        </head>
        <body>
        <h2>init() & destroy()</h2>
        """);

    if (itemId == null || itemName == null) {
      out.println("<p>エラー: 商品IDと商品名の両方を指定してください。</p>");
    } else {
      cache.put(itemId, itemName);
      out.println("<p>商品追加: 商品ID " + itemId + " -> " + itemName + "</p>");
    }

    out.println("""
        <a href="ex1.html">トップ</a>
        </body>
        </html>
        """);
  }

}
