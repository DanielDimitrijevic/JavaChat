package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Server_Conection  extends Thread{
	private Socket           socket    = null;
	private int              ID        = -1;
	private DataInputStream  streamIn  =  null;
	private DataOutputStream streamOut = null;
	private Server_Controller sc;
	
	public Server_Conection(Server_Controller sc , Socket socket){
		super();
		this.sc = sc;
		this.socket = socket;
		ID = socket.getPort();
	}
	public int getID()
	   {  return ID;
	   }
	   public void run()
	   {  System.out.println("Server Thread " + ID + " running.");
	      while (true)
	      {  try
	         {  server.handle(ID, streamIn.readUTF());
	         }
	         catch(IOException ioe)
	         {  System.out.println(ID + " ERROR reading: " + ioe.getMessage());
	            server.remove(ID);
	            stop();
	         }
	      }
	   }
	   public void open() throws IOException
	   {  streamIn = new DataInputStream(new 
	                        BufferedInputStream(socket.getInputStream()));
	      streamOut = new DataOutputStream(new
	                        BufferedOutputStream(socket.getOutputStream()));
	   }
	   public void close() throws IOException
	   {  if (socket != null)    socket.close();
	      if (streamIn != null)  streamIn.close();
	      if (streamOut != null) streamOut.close();
	   }
}
