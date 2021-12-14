import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	//function that creates bytes server
	static void byteServer() throws IOException {
		ServerSocket ss = new ServerSocket(1234);
		System.out.println("server is started !");
		System.out.println("waiting for a connection from client...");
		
		Socket s = ss.accept(); //attente de connexion
		System.out.println("connection is established !");
		
		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		int nb = is.read(); //attente d’un octet
		System.out.println("the read number is " + nb);
		//just some dummy processing
		int rep = nb * 3;
		os.write(rep);
		System.out.println("sent response to client.");
	}
	
	//function that creates 'string' server
	static void charServer() throws IOException {
		ServerSocket ss = new ServerSocket(2345);
		System.out.println("server is started !");
		System.out.println("waiting for a connection from client...");
		
		Socket sock = ss.accept();
		System.out.println("connection is established !");
		
		InputStream is = sock.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		System.out.println("received string is: \""+ s +"\"");
		//responding by the same message but in upper cases
		OutputStream os = sock.getOutputStream();
		PrintWriter pw = new PrintWriter(os, true); //true permet d’envoyer les chaines directement sans buffer
		pw.println(s.toUpperCase());
	}
	
	  //function that object server
	static void objServer() throws IOException, ClassNotFoundException {
		ServerSocket ss = new ServerSocket(3456);
		System.out.println("server is started !");
		System.out.println("waiting for a connection from client...");
		
		Socket sock = ss.accept();
		System.out.println("connection is established !");
		
		InputStream is = sock.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		Planet pl = (Planet) ois.readObject();
		System.out.println("received object is:  Planet");
		System.out.println("\tname: "+ pl.name);		
		System.out.println("\tnumber of inhabitants: "+ pl.nbr_inhab);	
		System.out.println("\tsurface gravity: g = "+ pl.surface_gravity);	
	
		OutputStream os = sock.getOutputStream();
		PrintWriter pw = new PrintWriter(os, true); //true permet d’envoyer les chaines directement sans buffer
		pw.println("Object received successfully.");
	}
	
	//main function calls one of the servers to be started
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		  // put here the function of the server we want to create
		charServer();
	}
	
}
