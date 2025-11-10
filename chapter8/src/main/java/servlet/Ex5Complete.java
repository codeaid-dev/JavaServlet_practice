package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ex5complete")
public class Ex5Complete extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String name = (String) session.getAttribute("name");
    String address = (String) session.getAttribute("address");
    String product = (String) session.getAttribute("product");
    if (name == null || address == null || product == null
        || name.isEmpty() || address.isEmpty() || product.isEmpty()) {
      response.sendRedirect("ex5step1.jsp");
      return;
    }
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>セッションスコープ</title>
        </head>
        <body>
        <h2>セッションスコープ</h2>
        <p>以下の注文を受け付けました。</p>
        <ul>
        """);
    out.println("<li><strong>名前:</strong> " + name + "</li>");
    out.println("<li><strong>住所:</strong> " + address + "</li>");
    out.println("<li><strong>商品:</strong> " + product + "</li>");
    // セッションのデータを削除
    session.removeAttribute("name");
    session.removeAttribute("address");
    session.removeAttribute("product");
    out.println("""
        </ul>
        <p><a href="ex5step1.jsp">新たに注文する</a></p>
        </body>
        </html>
        """);
  }

}
