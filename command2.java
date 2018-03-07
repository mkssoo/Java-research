import java.io.*;

public class command2 {
  public static void main(String[] args) throws Exception {

      //ProcessBuilder pb = new ProcessBuilder("java", "-version");
      ProcessBuilder pb = new ProcessBuilder("ls", "-l");
      //pb.redirectErrorStream(true);
      Process p = pb.start();

      BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

      String line;
      while((line = buf.readLine()) != null) {
        System.out.println(line);
      }
  }
}