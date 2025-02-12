package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex5")
public class Ex5 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    if (application.getAttribute("zipdata") != null) {
      application.removeAttribute("zipdata");
    }
    request.getRequestDispatcher("/WEB-INF/jsp/ex5.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    Map<String, String> zipdata = (Map<String, String>) application.getAttribute("zipdata");
    if (zipdata == null) {
      zipdata = new HashMap<>();
    } else {
      zipdata.clear();
    }
    String zip1 = request.getParameter("zip1");
    zip1 = zip1 != null ? zip1 : "";
    String zip2 = request.getParameter("zip2");
    zip2 = zip2 != null ? zip2 : "";
    String city = request.getParameter("city");
    city = city != null ? city : "";
    String place = request.getParameter("place");
    place = place != null ? place : "";
    String ziptocity = request.getParameter("ziptocity");
    String citytozip = request.getParameter("citytozip");
    InputStream file = application.getResourceAsStream("/WEB-INF/27OSAKA.CSV");
    String line = "";
    BufferedReader br = new BufferedReader(new InputStreamReader(file, "Shift-JIS"));
    while ((line = br.readLine()) != null) {
      String[] str = line.replaceAll("\"", "").split(",");
      if (ziptocity != null) {
        if (!zip1.isEmpty()) {
          if (!zip2.isEmpty() && str[2].trim().equals(zip1 + zip2)) {
            zipdata.put("zip1", zip1);
            zipdata.put("zip2", zip2);
            zipdata.put("city", str[7]);
            zipdata.put("place", str[8]);
            break;
          } else if (zip2.isEmpty() && str[1].trim().equals(zip1)) {
            zipdata.put("zip1", zip1);
            zipdata.put("zip2", "");
            zipdata.put("city", str[7]);
            zipdata.put("place", "");
            break;
          }
        }
      } else if (citytozip != null) {
        if (!city.isEmpty()) {
          if (!place.isEmpty() && str[7].trim().equals(city) && str[8].trim().equals(place)) {
            zipdata.put("zip1", str[1]);
            zipdata.put("zip2", str[2].trim().substring(3));
            zipdata.put("city", city);
            zipdata.put("place", place);
            break;
          } else if (place.isEmpty() && str[7].equals(city)) {
            zipdata.put("zip1", str[1]);
            zipdata.put("zip2", "");
            zipdata.put("city", city);
            zipdata.put("place", "");
            break;
          }
        }
      }
    }

    application.setAttribute("zipdata", zipdata);
    request.getRequestDispatcher("/WEB-INF/jsp/ex5.jsp").forward(request, response);
  }

}
