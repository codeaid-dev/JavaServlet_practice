package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Review;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/review.jsp");
    rd.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    ServletContext application = getServletContext();
    HttpSession session = request.getSession();

    String user = (String) session.getAttribute("user");
    if (user == null) {
      response.sendRedirect(request.getContextPath());
      return;
    }

    String product = request.getParameter("product");
    String comment = request.getParameter("comment");

    if (product == null || product.isEmpty() || comment == null || comment.isEmpty()) {
      request.setAttribute("error", "商品名とレビュー内容を入力してください。");
      RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/review.jsp");
      rd.forward(request, response);
      return;
    }

    // レビューリストをアプリケーションスコープから取得
    List<Review> list = (List<Review>) application.getAttribute("reviewList");
    if (list == null) {
      list = Collections.synchronizedList(new ArrayList<>());
      application.setAttribute("reviewList", list);
    }

    // 新しいレビューを追加
    Review review = new Review(user, product, comment);
    list.add(review);

    response.sendRedirect("review");
  }

}
