import java.io.*;
import java.net.*;
public class http_get1 {
  public static void main(String[] args) throws Exception {
    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    String s;
    System.out.println("address:");
    String address=buf.readLine();

    System.out.println("path:");
    String path=buf.readLine();

    Socket sock = new Socket(address,80);
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
    BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

    out.write("GET"+" "+path +" HTTP/1.0\r\n");
    out.flush();

    out.write("HOST: "+address+"\r\n");
    out.flush();

    out.write("User-Agent: firefoxchrome ver.1.0.5 on Wii(G)\r\n");
    out.flush();

    out.write("\r\n");
    out.flush();

    System.out.println("--------------------------------------------------------------");
    while((s=in.readLine())!=null){
      System.out.println(s);
    }
    System.out.println("--------------------------------------------------------------");

    sock.close();
  }
}
