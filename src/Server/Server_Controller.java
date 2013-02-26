package Server;

import java.net.Socket;
import java.util.ArrayList;

import Frame.MyFrame;

public class Server_Controller {
	private Server_GUI sg;
	private MyFrame mf;
	private ArrayList<Server_Conection> clients =new  ArrayList<Server_Conection>();
	private Server_ConectionReader scr;
	
	public Server_Controller(){
		sg = new Server_GUI(this);
		mf = new MyFrame(sg, "Server",true);
		scr = new Server_ConectionReader(this,1234);
	}
	public void addClient(Socket socket){
		System.out.println("Client accepted: " + socket);
		clients.add(new Server_Conection(this,socket));
	}
	public void handle(int id, String txt){
		if(txt.charAt(0) == '/'){
			String [] s =txt.split(" ");
			if(s[0] == "/nn"){
				String name="";
				for(int i = 1; i < s.length;i++)
					name += s[i];
				sg.addUser(name);
			}
		}else{
			sg.addMessage(txt, "Test1");
			for(int i = 0; i < clients.size();i++){
				clients.get(i).sendMessage(txt);
			}
		}
	}
	
	public void remove(int id){
		
	}
	
	public static void main(String[] args){
		new Server_Controller();
	}
}
