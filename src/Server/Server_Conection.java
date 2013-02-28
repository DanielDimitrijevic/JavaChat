package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server_Conection  extends Thread{
	private Socket           socket    = null;
	private int              ID        = -1;
	private BufferedReader  streamIn  =  null;
	private PrintWriter streamOut = null;
	private Server_Controller sc;
	private String uname;
	
	public Server_Conection(Server_Controller sc , Socket socket){
		super();
		this.sc = sc;
		this.socket = socket;
		ID = socket.getPort();
		uname = "New User";
		try{
			this.open();
		}catch(IOException e){
			System.err.println(e.getMessage());
		}
		this.start();
	}
	public void run(){
		System.out.println("Server Thread " + ID + " running.");
    	while (!this.interrupted()){
    		try {
    			  Thread.sleep(10);
    			}
    			catch (InterruptedException e) {}
    		try{
    			
    			if(streamIn.ready())
    				sc.handle(ID, streamIn.readLine());
    		}catch(Exception ioe){
    			System.out.println(ID + " ERROR reading: " + ioe.getMessage());
	            sc.remove(ID);
	            interrupt();
	        }
    	}
	}
	public void open() throws IOException{
		streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    streamOut = new PrintWriter(socket.getOutputStream(), true);
	}
	public void close() throws IOException{
		if (socket != null)    socket.close();
		if (streamIn != null)  streamIn.close();
		if (streamOut != null) streamOut.close();
		this.interrupt();
		this.stop();
	}
	
	public void sendMessage(String msg){
		try{
			streamOut.print(msg + "\n");
			streamOut.flush();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	public int getID(){return this.ID;}
	public String getUName(){return this.uname;}
	public void setUName(String un){this.uname = un;}
}
