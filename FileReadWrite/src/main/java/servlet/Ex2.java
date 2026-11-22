package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ex2")
public class Ex2 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    File memoFile = getMemoFile();
    List<String> memoList = readMemos(memoFile);
    request.setAttribute("memoList", memoList);
    request.getRequestDispatcher("/WEB-INF/jsp/ex2.jsp")
           .forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String memo = request.getParameter("memo");
    // 保存ファイルを取得
    File memoFile = getMemoFile();
    String message;
    String messageType;
    // 入力チェック
    if (memo == null || memo.isBlank()) {
      message = "メモを入力してください。";
      messageType = "error";
    } else {
      // ファイルに追記
      try (
        BufferedWriter bw = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(memoFile, true),
                StandardCharsets.UTF_8))
      ) {
        bw.write(memo.strip());
        bw.newLine(); // メモの最後に改行を追加
      }
      message = "メモを保存しました。";
      messageType = "success";
    }
    // 保存済みのメモを読み込む
    List<String> memoList = readMemos(memoFile);
    // JSPにデータを渡す
    request.setAttribute("memoList", memoList);
    request.setAttribute("message", message);
    request.setAttribute("messageType", messageType);
    // JSPに画面表示を任せる
    request.getRequestDispatcher("/WEB-INF/jsp/ex2.jsp")
           .forward(request, response);
  }

  //保存ファイルのパスを取得
  private File getMemoFile() throws IOException {
    // Eclipse以外のとき
//    ServletContext context = getServletContext();
//    String dir = context.getRealPath("/WEB-INF/data");
//    if (dir == null) {
//      throw new IOException(
//          "保存先のパスを取得できません。");
//    }
//    File saveDir = new File(dir);
    // Webアプリ外の場所(ホームディレクトリ)で読み書きする場合
    String userHome = System.getProperty("user.home");
    File saveDir = new File(userHome, "Ex2Data");
    // ディレクトリが存在しなければ作成
    if (!saveDir.exists() && !saveDir.mkdirs()) {
      throw new IOException(
          "保存先ディレクトリを作成できません。");
    }
    return new File(saveDir, "memo.txt");
  }

  // メモファイルを読み込む
  private List<String> readMemos(File file)
          throws IOException {
    List<String> memoList = new ArrayList<>();
    if (!file.exists()) {
      return memoList;
    }
    try (
      BufferedReader br = new BufferedReader(
          new InputStreamReader(
              new FileInputStream(file),
              StandardCharsets.UTF_8))
    ) {
      String line;
      while ((line = br.readLine()) != null) {
        memoList.add(escape(line));
      }
    }
    return memoList;
  }

  // HTMLをエスケープ
  private String escape(String value) {
    return value
        .replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace("\"", "&quot;")
        .replace("'", "&#39;");
  }
}
