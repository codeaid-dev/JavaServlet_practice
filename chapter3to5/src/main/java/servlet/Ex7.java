package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex7")
public class Ex7 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String error = "";
    String msg = "";
    if (id == null || id.length() == 0) {
      error += "番号が入力されていません<br>";
    } else {
      msg += "番号: " + id + "<br>";
    }
    if (name == null || name.length() == 0) {
      error += "名前が入力されていません<br>";
    } else {
      msg += "名前: " + name + "<br>";
    }
    if (error.length() != 0) {
      msg = error + msg;
    }
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>サーブレットとJSP基本</title>
        </head>
        <body>
          <h2>POST送信 & 受信</h2>
          <p>
        """ + msg + """
          </p>
        </body>
        </html>
        """);
  }

}
