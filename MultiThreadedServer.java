import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {
	private final Socket clientSocket;
	static int client_nbr = 0;
	static String MyClientNbr;
	  
    // Constructor
    public MultiThreadedServer(Socket socket) {
        this.clientSocket = socket;
    }

  //main function implementation
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(2345);
		System.out.println("server is started !\nThis server supports up to 4 connections.");
		System.out.println("waiting for a connection from clients...\n");
		while(true) {
			Socket sock = ss.accept();
			client_nbr++;  client_nbr=client_nbr%5;
			MyClientNbr = "You are client number " + client_nbr;
			System.out.println("new connection from client number "+ client_nbr +" with address: " + sock.getInetAddress().getHostAddress());
			
			OutputStream os = sock.getOutputStream();
        	PrintWriter pw = new PrintWriter(os, true);
        	pw.println("successfully connected to MultiThreaded server.");
        	pw.println(MyClientNbr);
			
			MultiThreadedServer client1 = new MultiThreadedServer(sock);
			MultiThreadedServer client2 = new MultiThreadedServer(sock);
			MultiThreadedServer client3 = new MultiThreadedServer(sock);
			MultiThreadedServer client4 = new MultiThreadedServer(sock);
		    // These threads will handle the clients separately
		    new Thread(client1).start();
		    new Thread(client2).start();
		    new Thread(client3).start();
		    new Thread(client4).start();
		}
	}
		
	
	public void run() {
        try {         
              // get the outputstream of client
        	OutputStream os = clientSocket.getOutputStream();
        	PrintWriter pw = new PrintWriter(os, true);

              // get the inputstream of client
            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
    		BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != "") {
                // writing the received message from client
                System.out.println(" Sent from client: "+ line);
                pw.println("message received.");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
}
