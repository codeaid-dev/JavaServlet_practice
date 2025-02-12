package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Data;

@WebServlet("/ex2")
public class Ex2 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/jsp/ex2.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String strTotal = request.getParameter("total");
    String strPeople = request.getParameter("people");
    String fraction = request.getParameter("fraction");
    int total = strTotal != null ? Integer.valueOf(strTotal) : 0;
    int people = strPeople != null ? Integer.valueOf(strPeople) : 0;
    Data data1 = new Data("参加者", people - 1, 0);
    Data data2 = new Data("代表", 1, 0);
    Data data3 = new Data("合計", people, 0);
    List<Data> result = new ArrayList<>();
    result.add(data1);
    result.add(data2);
    result.add(data3);
    int surplus = (total / people) % 100;
    if (surplus == 0) {
      result.get(0).setPrice(total / people);
      result.get(1).setPrice(total / people);
    } else if (fraction.equals("more")) {
      result.get(0).setPrice((total / people / 100) * 100);
      result.get(1).setPrice(total - result.get(0).getPrice() * (people - 1));
    } else {
      result.get(0).setPrice((total / people / 100) * 100 + 100);
      result.get(1).setPrice(total - result.get(0).getPrice() * (people - 1));
    }
    result.get(2).setPrice(total);

    request.setAttribute("fraction", fraction);
    request.setAttribute("result", result);
    request.getRequestDispatcher("/WEB-INF/jsp/ex2.jsp").forward(request, response);
  }

}
