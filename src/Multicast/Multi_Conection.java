package Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class Multi_Conection extends Thread{
	private MulticastSocket socket;
	private InetAddress group;
	private int port;
	private DatagramPacket outgoing, incoming;
	private Multi_Controller c;
	
	public Multi_Conection(Multi_Controller c,InetAddress g, int port){
		this.c = c;
		this.group = g;
		this.port = port;
		try {
			socket = new MulticastSocket (port);
			socket.setTimeToLive (5);
		    socket.joinGroup (group);
		    socket.setNetworkInterface(NetworkInterface.getByInetAddress(InetAddress.getByName("10.0.0.64")));
		    System.out.println(socket.getNetworkInterface());
		    outgoing = new DatagramPacket (new byte[1], 1, group, port);
		    incoming = new DatagramPacket (new byte[65508], 65508);
		    this.start();
		} catch (IOException e) {
			System.err.println("ERROR: Konte icht verbunden werden: \n " + e.getMessage() + "\n");
		}
	    
	}
	public void sendMessage(String msg){
		try {
			byte[] utf = msg.getBytes("UTF8");
			outgoing.setData (utf);
			outgoing.setLength (utf.length);
			socket.send (outgoing);
		} catch (IOException e) {
			System.err.println("Konnte Nachricht nicht Senden!");
		}
	}
	public void run(){
		try {
		      while (!Thread.interrupted ()) {
		        incoming.setLength (incoming.getData ().length);
		        socket.receive (incoming);
		        String message = new String
		          (incoming.getData (), 0, incoming.getLength (), "UTF8");
		        c.addMessage(message);
		      }
		    } catch (IOException ex) {
		    	try {
					socket.leaveGroup (group);
				} catch (IOException e) {
				}
		    	socket.close();
		    }
	}
}
