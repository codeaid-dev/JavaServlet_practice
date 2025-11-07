package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex15")
public class Ex15 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String yoko = request.getParameter("yoko");
    String tate = request.getParameter("tate");
    String moji = request.getParameter("moji");
    String error = "";
    String result = "";
    if (moji == null || (moji != null && moji.length() != 1)) {
      error += "1文字で入力してください。<br>";
    }
    try {
      int numY = Integer.parseInt(yoko);
      int numT = Integer.parseInt(tate);
      if (numY < 1 || numT < 1) {
        error += "横縦は1以上の数字を入力してください。";
      } else if (error.length() == 0) {
        for (int y = 0; y < numT; y++) {
          for (int x = 0; x < numY; x++) {
            result += moji;
          }
          result += "<br>";
        }
      }
    } catch (NumberFormatException e) {
      error += "横縦は1以上の数字を入力してください。";
    }
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習15</title>
        </head>
        <body>
          <h2>実習15</h2>
          <p>
          """);
    if (error.length() != 0) {
      out.println(error);
    }
    if (result.length() != 0) {
      out.println(result);
    }
    out.println("""
        </p>
        </body>
        </html>
        """);
  }

}
