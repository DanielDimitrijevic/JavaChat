package Multicast;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Interfaces.Controller;
import Listeners.KeyListener;
import Listeners.SelListener;
import Listeners.WindowListener;

public class Multi_Controller implements Controller{
	private InetAddress group;
	private int port;
	private ArrayList<String> clients = new ArrayList<String>();
	private Multi_GUI g;
	private KeyListener kl;
	private SelListener sl;
	private WindowListener wl;
	private Multi_Conection c;
	
	public Multi_Controller(String add, int port){
		try{
			kl = new KeyListener(this);
			sl = new SelListener(this);
			wl = new WindowListener(this);
			group = InetAddress.getByName(add);
			this.port = port;
			g = new Multi_GUI(this,kl,sl,wl);
			
			c = new Multi_Conection(this,group,port);
			
			g.open();
		}catch (UnknownHostException e) {
			System.err.println("Die Multicast Adresse ist nicht bekannt\n" + e.getMessage() + "\n");
		}
	}
	public void sendAll(String txt, String name){
		c.sendMessage(name + ":" + txt);
	}
	
	

	@Override
	public void handle(int id, String msg) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyEvent() {
		this.sendAll(g.getInput(), "Server");
		//g.addMessage(g.getInput(), "Server");
		g.setInput("");
		
	}


	@Override
	public void selectEvent(int id) {
		switch (id) {
		case 0: {
			this.sendAll(g.getInput(), "Server");
			//g.addMessage(g.getInput(), "Server");
			g.setInput("");
		}
		break;
		}
	}


	@Override
	public void disconect() {
		// TODO Auto-generated method stub
		
	}
	public void addMessage(String msg){
		g.addMessage(msg, "");
	}
	
	public static void main(String[] args){
		new Multi_Controller("224.113.21.3",1357);
	}
}