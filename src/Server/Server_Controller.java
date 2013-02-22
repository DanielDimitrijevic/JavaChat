package Server;

import java.net.Socket;
import java.util.ArrayList;

import Frame.MyFrame;

public class Server_Controller {
	private Server_GUI sg;
	private MyFrame mf;
	private ArrayList<Server_Conection> clients =new  ArrayList<Server_Conection>();
	
	public Server_Controller(){
		sg = new Server_GUI(this);
		mf = new MyFrame(sg, "Server",true);
	}
	public void addThread(Socket socket){
		System.out.println("Client accepted: " + socket);
		clients.add(new Server_Conection(this,socket));
	}
	
	public static void main(String[] args){
		new Server_Controller();
	}
}
