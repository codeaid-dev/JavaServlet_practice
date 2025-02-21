package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Select;

@WebServlet("/ex7")
public class Ex7 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    if (application.getAttribute("select") != null) {
      application.removeAttribute("select");
    }
    if (application.getAttribute("result") != null) {
      application.removeAttribute("result");
    }
    List<String> selectList = new ArrayList<>();
    selectList.add("あかさたないきしちにうくすつぬえけせてね");
    selectList.add("ぴゃぴぃぴゅぴぇぴょ");
    selectList.add("てゃてぃてゅてぇてょ");
    selectList.add("うぁうぃうぇうぉ");
    selectList.add("ぐぁげぇごぉぉぉ");
    Random random = new Random();
    int index = random.nextInt(selectList.size());
    Select select = new Select(selectList.get(index), 5 - selectList.size() + 1);
    application.setAttribute("select", select);
    selectList.remove(index);
    application.setAttribute("selectList", selectList);
    application.setAttribute("start", System.currentTimeMillis());

    request.getRequestDispatcher("/WEB-INF/jsp/ex7.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String typing = request.getParameter("typing");
    typing = typing != null ? typing : "";
    ServletContext application = getServletContext();
    List<String> selectList = (List<String>) application.getAttribute("selectList");
    Select select = (Select) application.getAttribute("select");
    if (typing.equals(select.getText())) {
      if (selectList.size() != 0) {
        Random random = new Random();
        int index = random.nextInt(selectList.size());
        select = new Select(selectList.get(index), 5 - selectList.size() + 1);
        application.setAttribute("select", select);
        selectList.remove(index);
        application.setAttribute("selectList", selectList);
      } else {
        // 経過時間を表示
        long end = System.currentTimeMillis();
        long spend = end - (Long) application.getAttribute("start");
        String result = "終了！！経過時間 : " + spend / 1000 + "秒";
        application.setAttribute("result", result);
      }
    }

    request.getRequestDispatcher("/WEB-INF/jsp/ex7.jsp").forward(request, response);
  }

}
