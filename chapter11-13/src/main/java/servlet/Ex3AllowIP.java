package servlet;

import java.io.IOException;
import java.util.Set;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex3allowip")
public class Ex3AllowIP extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/jsp/ex3.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    Set<String> allowedIPs = (Set<String>) application.getAttribute("allowedIPs");
    String IPAddress = request.getParameter("ip");
    if (allowedIPs != null && IPAddress != null && !IPAddress.isEmpty()) {
      allowedIPs.add(IPAddress);
    }
    request.getRequestDispatcher("/WEB-INF/jsp/ex3.jsp").forward(request, response);
  }

}
