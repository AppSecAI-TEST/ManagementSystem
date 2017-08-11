import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ConnectionThread extends Thread{
	private Socket sock;
	private FileInputStream fis = null;
	private FileOutputStream fos = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;

	public ConnectionThread(Socket sock){
		try{
			this.sock = sock;
			this.dos = new DataOutputStream(sock.getOutputStream());
		}catch(Exception e){}
	}

	public void run(){
		System.out.println(sock.getInetAddress() + " ´Ô Á¢¼Ó");

	}
}

public class Server {
	
	public static void main(String[] args) throws Exception {
		
		ServerSocket server = new ServerSocket(40000);

		while(true){
			Socket client = server.accept();
			new ConnectionThread(client).start();
		}
		
	}

}
