package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex8")
public class Ex8 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String message = request.getParameter("message");
    String color = request.getParameter("color");
    String error = "";
    String msg = "";
    if (message == null || message.length() == 0) {
      error += "入力されていません<br>";
    } else {
      msg += "メッセージ: " + message + "<br>";
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
          <h2>選択色でテキスト表示</h2>
          """);
    out.println("<p style=\"color:" + color + ";\">" + msg + "</p>");
    out.println("""
        </body>
        </html>
        """);
  }

}
