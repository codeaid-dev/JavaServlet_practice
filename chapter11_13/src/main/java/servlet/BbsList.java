package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DBUtil;
import model.Post;

@WebServlet("/bbs")
public class BbsList extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Post> posts = DBUtil.getAllPosts();
    request.setAttribute("posts", posts);

    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/bbs.jsp");
    rd.forward(request, response);
  }

}
