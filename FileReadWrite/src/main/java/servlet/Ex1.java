package servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

@WebServlet("/ex1")
public class Ex1 extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/plain; charset=UTF-8");
    ServletContext context = getServletContext();
    ServletContext application = getServletContext();

    InputStream inputStream = context.getResourceAsStream("/WEB-INF/data/source.txt");

    if (inputStream == null) {
      throw new IOException("/WEB-INF/data/source.txt が見つかりません。");
    }

    try (
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
      String line;
      String source="";
      while ((line = br.readLine()) != null) {
        source += line + "<br>";
      }
      application.setAttribute("source", source);
    }

    request.getRequestDispatcher("/WEB-INF/jsp/ex1.jsp")
        .forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String text = request.getParameter("text");
//    ServletContext context = getServletContext();

    // Webアプリ外の安全なディレクトリに保存する（ユーザーディレクトリ配下）
    String userHome = System.getProperty("user.home");
    File saveDir = new File(userHome, "Exercise1Data");
    if (!saveDir.exists() && !saveDir.mkdirs()) {
      throw new IOException("保存先ディレクトリを作成できません: "
              + saveDir.getAbsolutePath());
    }
    File resultFile = new File(saveDir, "result.txt");
    // テキストエリアの内容をファイルに書き込む（上書きモード）
    try (
      BufferedWriter bw = new BufferedWriter(
          new OutputStreamWriter(new FileOutputStream(resultFile, false), "UTF-8"))) {
      // 出力ファイルに書き込む
      bw.write(text);
    }
    request.setAttribute("result", "テキストエリアの内容を保存しました。");

    request.getRequestDispatcher("/WEB-INF/jsp/ex1.jsp")
        .forward(request, response);
  }
}
