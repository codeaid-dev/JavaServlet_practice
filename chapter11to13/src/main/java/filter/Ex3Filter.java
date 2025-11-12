package filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/ex3secure")
public class Ex3Filter extends HttpFilter {

  public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    ServletContext application = getServletContext();
    Set<String> allowedIPs = (Set<String>) application.getAttribute("allowedIPs");
    if (allowedIPs == null) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("""
          <!DOCTYPE html>
          <html>
          <head>
          <meta charset="UTF-8">
          <title>フィルタ・リスナー</title>
          </head>
          <body>
          <h2>フィルタ・リスナー</h2>5
          <p>許可IPリストが取得できません。</p>
          </body>
          </html>
          """);
      return;
    }

    String clientIP = request.getRemoteAddr();
    if (!allowedIPs.contains(clientIP)) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("""
          <!DOCTYPE html>
          <html>
          <head>
          <meta charset="UTF-8">
          <title>フィルタ・リスナー</title>
          </head>
          <body>
          <h2>フィルタ・リスナー</h2>
          <h3>アクセス拒否</h3>
          """);
      out.println("<p>あなたのIPアドレス（" + clientIP + "）は許可されていません。</p>");
      out.println("""
          </body>
          </html>
          """);
      return;
    }

    chain.doFilter(request, response);
  }

}
