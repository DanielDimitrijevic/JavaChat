package BorkoChat;

import java.lang.*;
import java.io.*;
import java.net.*;

public class TCPChatCom implements Runnable {

  private ServerSocket defSocket = null;
  private Socket srvSocket = null;
  private BufferedReader inStream = null;
  private PrintWriter outStream = null;
  private TCPChatGUI guiChat = null;
  private Thread process = null;

  TCPChatCom() {
  }

  public void run() { 
    System.out.println("Verbindung hergestellt");
    try {
      while( true ) {
	try {
	  Thread.sleep(10);
	}
	catch (InterruptedException e) {}

	if (inStream.ready()) {
	  System.out.println("erhalten");
	  String s = inStream.readLine();
	  if ((s != null) &&  (s.length() != 0)) {
	    guiChat.addMessage( s );
	  }

	}
      }
    }
    catch(Exception e) {
      System.out.println("Fehler aufgetreten");
    }

    try {
      inStream.close();
      outStream.close();
      srvSocket.close();
      if ( defSocket != null )
	defSocket.close();
      System.out.println("Verbindung beendet");
    }
    catch(Exception e) {
      System.out.println("Fehler aufgetreten");
    }

  }

  public void startServer() {
    try {
      System.out.println("startServer() -> Server wartet auf Verbindung");
      defSocket = new ServerSocket( 1234 );
      srvSocket = defSocket.accept();
      inStream = new BufferedReader(new InputStreamReader(srvSocket.getInputStream()));
      outStream = new PrintWriter(srvSocket.getOutputStream(), true);
      process = new Thread( this );
      process.start();
    }
    catch(Exception e) {
      System.out.println("TCPChatCom startServer(): Fehler aufgetreten");
    }
  }

  public void startConnection( ) {
    try {
      srvSocket = new Socket("localhost", 1234 );
      inStream = new BufferedReader(new InputStreamReader(srvSocket.getInputStream()));
      outStream = new PrintWriter(srvSocket.getOutputStream(), true);
      process = new Thread( this );
      process.start();
    }
    catch(Exception e) {
      System.out.println("TCPChatCom startConnection(): Fehler aufgetreten");
    }
  }

  public void stopConnection( ) {
    try {
      process.stop();
      inStream.close();
      outStream.close();
      srvSocket.close();
      if ( defSocket != null )
	defSocket.close();
    }
    catch(Exception e) {
      System.out.println("TCPChatCom stopConnection(): Fehler aufgetreten");
    }
  }

  public void sendMessage( String inMessage ) {
    try {
      System.out.println("Sende Nachricht" + inMessage );
      outStream.print( inMessage );
      outStream.flush();
    }
    catch(Exception e) {
      System.out.println("TCPChatCom sendMessage(): Fehler aufgetreten");
    }
  }

  public void setGUI( TCPChatGUI inGuiChat ) {
    guiChat = inGuiChat;
  }
}
