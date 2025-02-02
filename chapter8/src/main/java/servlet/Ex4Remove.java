package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ex4remove")
public class Ex4Remove extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    List<String> cart = (List<String>) session.getAttribute("cart");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習4</title>
        </head>
        <body>
        <h2>実習4</h2>
        """);
    if (cart != null) {
      out.println("""
          <form action="ex4remove" method="post">
          <p>
          """);
      for (String item : cart) {
        out.println("<label><input type='checkbox' name='removeItems' value='" + item + "'>" + item + "</label><br>");
      }
      out.println("""
          </p>
          <p><button type="submit">選択した商品を削除</button>
          <button type="submit" name="removeAll">商品を全て削除</button></p>
          </form>
          """);
    }
    out.println("""
        <a href="ex4cart">カートに戻る</a>
        </body>
        </html>
        """);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (request.getParameter("removeAll") != null) {
      session.removeAttribute("cart");
      response.sendRedirect("ex4cart");
      return;
    }
    List<String> cart = (List<String>) session.getAttribute("cart");

    if (cart != null) {
      String[] removeItems = request.getParameterValues("removeItems");

      if (removeItems != null) {
        for (String item : removeItems) {
          cart.remove(item);
        }
      }

      session.setAttribute("cart", cart);
    }

    response.sendRedirect("ex4cart");
  }

}
