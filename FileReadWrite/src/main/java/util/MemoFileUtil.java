
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class MemoFileUtil {
  private static File getMemoFile() throws IOException {
    String userHome = System.getProperty("user.home");
    File saveDir = new File(userHome, "Ex3Data");
    if (!saveDir.exists() && !saveDir.mkdirs()) {
      throw new IOException("保存フォルダを作成できません。");
    }
    return new File(saveDir, "memos.txt");
  }

  public static List<String[]> readMemos() throws IOException {
    List<String[]> memos = new ArrayList<>();
    File memoFile = getMemoFile();
    if (!memoFile.exists()) {
      return memos;
    }
    try (
      BufferedReader br = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(memoFile),
          StandardCharsets.UTF_8
        )
      )
    ) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] data = line.split("\t", -1);
        if (data.length != 4) {
          continue;
        }
        String id = decode(data[0]);
        String title = decode(data[1]);
        String body = decode(data[2]);
        String date = decode(data[3]);
        memos.add(new String[] {
          id, title, body, date
        });
      }
    }
    return memos;
  }

  public static void saveMemos(List<String[]> memos)
          throws IOException {
    File memoFile = getMemoFile();
    try (
      BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(
          new FileOutputStream(memoFile, false),
          StandardCharsets.UTF_8
        )
      )
    ) {
      for (String[] memo : memos) {
        bw.write(encode(memo[0]));
        bw.write("\t");
        bw.write(encode(memo[1]));
        bw.write("\t");
        bw.write(encode(memo[2]));
        bw.write("\t");
        bw.write(encode(memo[3]));
        bw.newLine();
      }
    }
  }

  private static String encode(String value) {
    return Base64.getEncoder().encodeToString(
      value.getBytes(StandardCharsets.UTF_8)
    );
  }

  private static String decode(String value) {
    return new String(
      Base64.getDecoder().decode(value),
      StandardCharsets.UTF_8
    );
  }
}
