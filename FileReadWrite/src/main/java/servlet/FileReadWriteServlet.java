package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class FileReadWriteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/plain; charset=UTF-8");
    PrintWriter out = response.getWriter();

    ServletContext context = getServletContext();

    // --------------------------------------
    //  1. 読み取り元ファイル（input.txt）
    // --------------------------------------
    // getResourceAsStream() を使うことで、Eclipse のデプロイ環境でも確実に読み取れる
    InputStream inputStream = context.getResourceAsStream("/WEB-INF/data/input.txt");

    if (inputStream == null) {
      out.println("エラー: /WEB-INF/data/input.txt が見つかりません。");
      return;
    }

    // --------------------------------------
    //  2. 書き込み先ファイル（output.txt）
    // --------------------------------------
    // Webアプリ外の安全なディレクトリに保存する（ユーザーディレクトリ配下）
    File saveDir;
    if (context.getRealPath("/").contains(".metadata")) {
      String userHome = System.getProperty("user.home");
      saveDir = new File(userHome, "MyWebAppData");
    } else {
      String dir = context.getRealPath("/WEB-INF/data");
      saveDir = new File(dir);
    }
    if (!saveDir.exists()) {
      saveDir.mkdirs();
    }
    File outputFile = new File(saveDir, "output.txt");

    // --------------------------------------
    //  3. 読み取り＋書き込み処理
    // --------------------------------------
    try (
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        BufferedWriter bw = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(outputFile, true), "UTF-8"))) {
      String line;
      while ((line = br.readLine()) != null) {
        // 出力ファイルに追記
        bw.write(line + " ← 読み込み済");
        bw.newLine();
      }
    }

    // --------------------------------------
    //  4. 結果をブラウザに出力
    // --------------------------------------
    out.println("ファイルの読み込みと書き込みが完了しました。");
    out.println();
    out.println("[入力元]");
    out.println("  /WEB-INF/data/input.txt");
    out.println("[出力先]");
    out.println("  " + outputFile.getAbsolutePath());

  }
}
