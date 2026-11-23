package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import util.MemoFileUtil;

@WebServlet("/ex3")
public class Ex3 extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<String[]> memos = MemoFileUtil.readMemos();
    request.setAttribute("memos", memos);
    request.getRequestDispatcher("/WEB-INF/jsp/ex3.jsp")
           .forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");
    List<String[]> memos = MemoFileUtil.readMemos();
    if ("add".equals(action)) {
      String title = request.getParameter("title");
      String body = request.getParameter("body");
      if (title != null && !title.isBlank()
              && body != null && !body.isBlank()) {
        int maxId = 0;
        for (String[] memo : memos) {
          maxId = Math.max(
            maxId,
            Integer.parseInt(memo[0])
          );
        }
        String id = String.valueOf(maxId + 1);
        String date = LocalDateTime.now()
            .format(FORMATTER);
        memos.add(new String[] {
          id,
          title.strip(),
          body.strip(),
          date
        });
        MemoFileUtil.saveMemos(memos);
      }
    } else if ("delete".equals(action)) {
      String deleteId = request.getParameter("id");
      memos.removeIf(
        memo -> memo[0].equals(deleteId)
      );
      MemoFileUtil.saveMemos(memos);
    }
    response.sendRedirect(request.getContextPath() + "/ex3");
  }

}
