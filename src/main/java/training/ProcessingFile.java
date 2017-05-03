package training;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class ProcessingFile {
  private static BufferedReader br;


  public static void WriteToEndFile(String path, Collection<String> list) {
    Writer writer = null;
    try {
      writer =
          new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "utf-8"));
      for (String line : list) {
        writer.write(line + "\r\n");
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }

  public static void close() {
    try {
      br.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public static void readFile(String path, int countScip) throws IOException {
    try {
      br = new BufferedReader(new FileReader(path));
      int i = 0;
      for (String line; i < countScip && (line = br.readLine()) != null; i++) {
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static List<String> nextWindow(int count) throws IOException {
    int i = 0;
    List<String> res = new ArrayList<String>();
    for (String line; i < count && (line = br.readLine()) != null; i++) {
      res.add(line);
    }
    return res;
  }

  public static BufferedWriter newWriteFile(String path)
      throws UnsupportedEncodingException, FileNotFoundException, IOException {
    return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
  }

  public static void writeLineToFile(BufferedWriter bwr, String line) throws IOException {
    bwr.write(line + "\r\n");
  }


  private static void writeFileLargeLL() throws IOException {
    try (Writer writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream("filename2.txt", true), "utf-8"))) {
      int countScip = 61000000;
      int count = 8000000;

      BufferedReader br = getBr("ALL.txt", countScip);

      int i = countScip;
      for (String line; (line = br.readLine()) != null && i < count + countScip; i++) {
        writer.write(line + "\r\n");
      }
    }
  }

  private static BufferedReader getBr(String path, int countScip) throws IOException {
    int i = 0;
    List<String> res = new ArrayList<String>();
    BufferedReader br = new BufferedReader(new FileReader(path));
    for (String line; i < countScip && (line = br.readLine()) != null; i++) {
    }
    return br;
  }


  private static final Pattern p = Pattern.compile("^.*[0-9]{6}.*$");

  private static void writeFileLarge(int count)
      throws UnsupportedEncodingException, FileNotFoundException, IOException {
    try (Writer writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream("vectors.model1.txt", true), "utf-8"))) {
      BufferedReader br = getBr("model123.txt", 0);

      // br.readLine();
      int i=0;
      for (String line; (line = br.readLine()) != null && i < count;i++ ) {
          writer.write(line + "\n");
      }
    }
  }

  public static List<String> readFileLarge(String path, int count, int countScip)
      throws IOException {
    int i = 0;
    List<String> res = new ArrayList<String>();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      for (String line; i < countScip && (line = br.readLine()) != null; i++) {
      }
      for (String line; (line = br.readLine()) != null && i < count + countScip; i++) {
        res.add(line);
      }
    }
    return res;
  }


  public static void main(String[] args)
      throws UnsupportedEncodingException, FileNotFoundException, IOException {
    if(args.length == 1) {
      writeFileLarge(Integer.valueOf(args[0]));
    }
  }
}
