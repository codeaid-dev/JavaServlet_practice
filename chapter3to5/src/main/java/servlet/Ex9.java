package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex9")
public class Ex9 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String result = "趣味:";
    String[] hobbies = request.getParameterValues("hobby");
    if (hobbies != null) {
      for (String hobby : hobbies) {
        result += " " + hobby;
      }
    } else {
      result = "趣味が選択されていません。";
    }
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>サーブレットとJSP基本</title>
        </head>
        <body>
          <h2>チェック項目の表示</h2>
          """);
    out.println(result);
    out.println("""
        </body>
        </html>
        """);
  }

}
