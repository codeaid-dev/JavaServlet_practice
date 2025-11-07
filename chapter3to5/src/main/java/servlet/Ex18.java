package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex18")
public class Ex18 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String[] registered = { "hoge@sample.com", "foo@sample.com" };
    String mail = request.getParameter("mail");
    mail = mail != null ? mail : "";
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習18</title>
        </head>
        <body>
          <h2>実習18</h2>
          <form action="ex18" method="post">
            <label>メールアドレス: <br>
            <input type="text" name="mail"
            """);
    out.println("value=\"" + mail + "\"></label><br>");
    out.println("""
          <p><button type="submit">登録</button></p>
        </form>
        """);
    if (mail.matches("^[\\w\\-.]+@[A-Za-z0-9.\\-]+\\.[a-zA-Z]{2,}$")) {
      boolean err = false;
      for (String m : registered) {
        if (m.equals(mail)) {
          err = true;
          break;
        }
      }
      if (!err) {
        out.println("<p>登録可能です。</p>");
      } else {
        out.println("<p>すでに登録されています。</p>");
      }
    } else {
      out.println("<p>登録できません。</p>");
    }
    out.println("""
        </body>
        </html>
        """);
  }

}
