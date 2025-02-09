package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex3")
public class Ex3 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String pageNum = request.getParameter("page");
    if (pageNum == null) {
      response.sendRedirect("/chapter9/ex3?page=1");
      return;
    }
    Integer num = Integer.valueOf(pageNum);
    if (num > 0 && num < 6) {
      ServletContext application = getServletContext();
      Map<Integer, Integer> pageViews = (Map<Integer, Integer>) application.getAttribute("pageViews");
      if (pageViews == null) {
        pageViews = new HashMap<>();
      }
      int count = pageViews.getOrDefault(num, 0) + 1;
      pageViews.put(num, count);
      application.setAttribute("pageViews", pageViews);
      request.setAttribute("currentPage", num);
    } else {
      String error = "ページはありません。";
      request.setAttribute("error", error);
    }

    request.getRequestDispatcher("WEB-INF/jsp/ex3.jsp").forward(request, response);
  }

}
