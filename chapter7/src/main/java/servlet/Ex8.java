package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex8")
public class Ex8 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String result = "";
    String[] registered = { "hoge@sample.com", "foo@sample.com", "bar@sample.com", "foobar@sample.com" };
    if (email.matches("^[\\w\\-.]+@[\\w\\-.]+\\.[a-zA-Z]+{2,}$")) {
      boolean err = false;
      for (String m : registered) {
        if (m.equals(email)) {
          err = true;
          break;
        }
      }
      if (!err) {
        result = "メールアドレスを登録しました。";
      } else {
        result = "メールアドレスは登録済みです。";
      }
    } else {
      result = "メールアドレスではありません。";
    }
    request.setAttribute("result", result);
    request.setAttribute("email", email);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex8.jsp");
    dispatcher.forward(request, response);
  }

}
