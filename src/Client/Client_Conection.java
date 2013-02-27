package Client;

import java.net.*;
import java.io.*;

public class Client_Conection extends Thread{
	private Socket socket              = null;
	private BufferedReader  streamIn   = null;
	private PrintWriter streamOut = null;
	private Client_Controller cc;

	public Client_Conection(String serverName, int serverPort, Client_Controller cc){
		super();
		this.cc = cc;
		System.out.println("Establishing connection. Please wait ...");
		try{
			socket = new Socket(serverName, serverPort);
			System.out.println("Connected: " + socket);
			startCon();
		}catch(UnknownHostException uhe){
			System.out.println("Host unknown: " + uhe.getMessage());
		}catch(IOException ioe){
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
    }
	public void startCon() throws IOException{
		streamIn   = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		streamOut = new PrintWriter(socket.getOutputStream(), true);
		start();
		
	}
	
	
	public void stopCom(){
		try{
			if (streamIn   != null)  streamIn.close();
			if (streamOut != null)  streamOut.close();
			if (socket    != null)  socket.close();
			stop();
		}catch(IOException ioe){
			System.out.println("Error closing ...");
		}
	}
	
	public void sendMessage(String msg){
		try{
			streamOut.print(msg + "\n");
			streamOut.flush();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		System.out.println("Client Thread  running.");
    	while (true){
    		try {
    			  Thread.sleep(10);
    			}
    			catch (InterruptedException e) {}
    		try{
    			if(streamIn.ready())
    			cc.handle(streamIn.readLine());
    		}catch(IOException ioe){
    			System.out.println(" ERROR reading: " + ioe.getMessage());
	            interrupt();
	        }
    	}
	}
}