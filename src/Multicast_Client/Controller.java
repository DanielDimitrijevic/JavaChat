package Multicast_Client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JPanel;

import Frame.MyFrame;

public class Controller {
	private InetAddress group;
	private int port;
	private ArrayList<String> clients = new ArrayList<String>();
	private GUI mg;
	private Conection c;
	
	public Controller(String add, int port){
		try {
			group = InetAddress.getByName(add);
			this.port=port;
			mg = new GUI(this);
			new MyFrame(mg,"Multicast (PTP)",true);
			c = new Conection(this,group,port);
		} catch (UnknownHostException e) {
			System.err.println("Die Multicast Adresse ist nicht bekannt\n" + e.getMessage() + "\n");
		}
	}
	public void sendMessage(String msg){
		//addMessage(msg);
		c.sendMessage(msg);
	}
	public void addMessage(String msg){
		mg.addText(msg);
	}
	
	
	public static void main(String[] args){
		new Controller("239.1.2.3",5432);
	}
}
