package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Server_Test.ConectedClient;

public class Server_ConectionReader implements Runnable{
	private Server_Controller sc;
	private ServerSocket server = null;
	private Thread       thread = null;
	
	public Server_ConectionReader(Server_Controller sc,int port){
		this.sc = sc;
		try{  
			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);  
			System.out.println("Server started: " + server);
			start();
		}catch(IOException ioe){  
			System.out.println("Can not bind to port " + port + ": " + ioe.getMessage()); 
		}
	}
	@Override
	public void run(){  
		while (thread != null){
			try{
				System.out.println("Waiting for a client ..."); 
				sc.addThread(server.accept()); 
			}catch(IOException ioe){
				System.out.println("Server accept error: " + ioe); stop();
			}
		}
   }
   public void start()  {
	   if (thread == null){
		   thread = new Thread(this); 
	       thread.start();
	   }
   }
   public void stop()   {
	   if (thread != null){
		   thread.stop();
	       thread = null;
	   }
   }
}
