package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex12")
public class Ex12 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    Map<Integer, String> members = new HashMap<>(Map.of(18, "鈴木次郎", 25, "田中幸一", 32, "佐藤さおり", 43, "高橋健太", 57, "山田太郎"));
    String teens = request.getParameter("teens");
    String twenties = request.getParameter("twenties");
    String thirties = request.getParameter("thirties");
    String forties = request.getParameter("forties");
    String result = "<ul>";
    for (Integer key : members.keySet()) {
      if (teens != null && key >= 10) {
        result += "<li>" + members.get(key) + " : " + key + "歳</li>";
      } else if (twenties != null && key >= 20) {
        result += "<li>" + members.get(key) + " : " + key + "歳</li>";
      } else if (thirties != null && key >= 30) {
        result += "<li>" + members.get(key) + " : " + key + "歳</li>";
      } else if (forties != null && key >= 40) {
        result += "<li>" + members.get(key) + " : " + key + "歳</li>";
      }
    }
    result += "</ul>";
    out.println("""
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="UTF-8">
        <title>実習12</title>
        </head>
        <body>
          <h2>実習12</h2>
          """);
    out.println(result);
    out.println("""
        </body>
        </html>
        """);
  }

}
