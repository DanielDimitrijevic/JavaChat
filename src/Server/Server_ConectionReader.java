package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Wartet auf Verbindungsanfragen von Clients
 * @author Dominik,Dimitrijevic
 *
 */
public class Server_ConectionReader implements Runnable{
	private Server_Controller sc;
	private ServerSocket server = null;
	private Thread       thread = null;
	/**
	 * KOntroller
	 * @param sc
	 * @param port
	 */
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
	/**
	 * Watet auf Anfragen
	 */
	@Override
	public void run(){  
		while (thread != null){
			try{
				System.out.println("Waiting for a client ..."); 
				sc.addClient(server.accept()); 
				sc.updateUser();
				sc.guiUpdate();
			}catch(IOException ioe){
				System.out.println("Server accept error: " + ioe); stop();
			}
		}
   }
	/**
	 * Startet den Thread
	 */
   public void start()  {
	   if (thread == null){
		   thread = new Thread(this); 
	       thread.start();
	   }
   }
   /**
    * Stopt den Thread
    */
   public void stop()   {
	   if (thread != null){
		   thread.interrupt();
	       thread = null;
	   }
   }
}
