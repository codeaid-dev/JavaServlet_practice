package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex2")
public class Ex2 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex2.jsp");
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    List<String> messages = (List<String>) application.getAttribute("messages");
    if (messages == null) {
      messages = new ArrayList<>();
    }
    String newMessage = request.getParameter("message");
    if (newMessage != null && !newMessage.strip().isEmpty()) {
      newMessage = newMessage.strip();
      messages.add(newMessage);
      application.setAttribute("messages", messages);
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex2.jsp");
    dispatcher.forward(request, response);
  }

}
