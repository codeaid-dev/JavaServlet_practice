package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex6")
public class Ex6 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習6</title>
        </head>
        <body>
          <h2>実習6</h2>
          <form action="ex6" method="post">
            ID:<br>
            <input type="text" name="id"><br>
            名前:<br>
            <input type="text" name="name"><br>
            <button type="submit">送信</button>
          </form>
        </body>
        </html>
        """);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String error = "";
    String msg = "";
    if (id == null || id.length() == 0) {
      error += "IDが入力されていません<br>";
    } else {
      msg += "ID: " + id + "<br>";
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
        <title>実習6</title>
        </head>
        <body>
          <h2>実習6</h2>
          <p>
        """ + msg + """
          </p>
        </body>
        </html>
        """);
  }

}
