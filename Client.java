import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	static Scanner clavier = new Scanner(System.in);

	//function that send bytes to the server
	static void sendByte() throws UnknownHostException, IOException {
		Socket s = new Socket("localhost" , 1234);
		System.out.println("successefully connected to the server.");
		System.out.println("This server read a number, multiply it by 3 and return the calculated value.\n");
		
		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		System.out.println("enter a number :");
		int number = clavier.nextInt();
		os.write(number);
		
		int rep = is.read();
		System.out.println("server responded: " + rep);
	}
	
	
	//function that send string characters to the server
	static void sendChar() throws UnknownHostException, IOException {
		Socket s = new Socket("localhost" , 2345);
		System.out.println("successfully connected to the server.");
		System.out.println("This server read a string and return it in upper cases.\n");
		
		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		System.out.print("enter text :");
		String txt = clavier.nextLine();
		PrintWriter pw = new PrintWriter(os, true); //true permet d’envoyer les chaines directement sans buffer
		pw.println(txt);
		
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String res = br.readLine();
		System.out.println("server responded: \""+ res +"\"");
	}
	

	//function that send object to the server
	static void sendObj() throws IOException {
		Socket sock = new Socket("localhost" , 3456);
		System.out.println("successfully connected to the server.");
		System.out.println("This server read a Planet object.\n");
		
		OutputStream os = sock.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		System.out.print("enter the planet name : ");
		String pl_name = clavier.nextLine();
		System.out.print("number of its inhabitants (in billions): ");
		double nbr_inhab = clavier.nextDouble();
		System.out.print("its surface gravity :  g = ");
		double g = clavier.nextDouble();
		Planet pl = new Planet(pl_name , nbr_inhab, g);
		oos.writeObject(pl);
		
		InputStream is = sock.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String res = br.readLine();
		System.out.println("server responded: \""+ res +"\"");
	}
	
	
	
	public static void main(String[] args) throws IOException {
		// put here the function of the client we want to connect to the server
		sendChar();
	}

}
