import java.io.*;
import java.net.*;

public class alert_receive2 {
	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(8000);

		while(true){
			Socket socket = server.accept();
			new MultiThread(socket).start();
		}
	}
}

class MultiThread extends Thread {

	Socket socketnum;
	public MultiThread(Socket socket){
		socketnum = socket;
	}

	public void run(){
		try{
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketnum.getOutputStream()));
			BufferedReader in  = new BufferedReader(new InputStreamReader(socketnum.getInputStream()));
			//ファイル書き込み
			//switchからのそのままの状態のalert
			BufferedWriter f_write1 = new BufferedWriter(new FileWriter("switch1.log",true));
			//整形されたデータ
			//BufferedWriter f_write2 = new BufferedWriter(new FileWriter("switch2.log",true));
			String s;

			s = in.readLine();
			//System.out.println(s);

			f_write1.write(s + "\n");
			//System.out.print(s + "\n");

			//スペース区切りで配列に格納
			String[] array = s.split(" ");

			String tmp = array[8].replace("{","");
			array[8] = tmp.replace("}","");
			//System.out.println(array[0]+" "+array[4]+" "+array[8]+" "+array[9]+" "+array[10]+" "+array[11]);
			//System.out.println(array[8]+" "+array[9]+" "+array[10]+" "+array[11]);
			//System.out.println(array[8]+" "+array[9]+" "+array[10]+" "+array[11]);

			//array[9]
			String array9Split[] = array[9].split(":");
			//System.out.println(array9Split[0]);
			//System.out.println(array9Split[1]);

			//array[11]
			String array11Split[] = array[11].split(":");
			//System.out.println(array11Split[0]);
			//System.out.println(array11Split[1]);

			//System.out.println(array[8]+" "+array9Split[0]+" "+array9Split[1]+" "+array[10]+" "+array11Split[0]+" "+array11Split[1]);

			String flows = array[8]+" "+array9Split[0]+" "+array9Split[1]+" "+array[10]+" "+array11Split[0]+" "+array11Split[1];
			//System.out.println(flows);

			//ProcessBuilder java = new ProcessBuilder("java", "flows_exec", flows);
			//Process p = java.start();

			out.write(flows + "\r\n");
			out.flush();

			f_write1.close();
			//f_write2.close();
			//end = System.currentTimeMillis();
			//System.out.println(end-start+"ミリ秒");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
