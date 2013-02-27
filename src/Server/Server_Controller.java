package Server;

import java.io.IOException;
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
			String [] s =txt.split(" ",2);
			System.out.println(s[1]);
			if(s[0].charAt(1)== 'n' && s[0].charAt(2)=='n'){
				for(int i = 0; i < clients.size();i++){
					if(id == clients.get(i).getID()){
						clients.get(i).setUName(s[1]);
						this.signalall(i);
					}
				}
				sg.update();
			}else if(s[0].charAt(1)=='e' && s[0].charAt(2)=='x' && s[0].charAt(3)=='i' && s[0].charAt(4)=='t'){
				for(int i = 0; i < clients.size(); i++){
					if(clients.get(i).getID() == id){
						try {
							clients.get(i).close();
						} catch (IOException e) {
							System.out.println("Verbindung konnte nicht beendet werden!");
						}
					}
				}
			}
		}else{
			String name ="";
			for(int i = 0; i < clients.size();i++)
				if(clients.get(i).getID()==id)
					name = clients.get(i).getUName();
			for(int i = 0; i < clients.size();i++){
				clients.get(i).sendMessage(name + ":"+txt);
			}
			sg.addMessage(txt, name);
		}
	}
	
	public void remove(int id){
		
	}
	public void updateUser(){
		for(int i = 0;i < clients.size();i++){
			for(int ii = 0; ii < clients.size();ii++){
				clients.get(ii).sendMessage("/nn " + i + " "+ clients.get(i).getUName());
			}
		}
	}
	public void signalall(int id){
		for(int i = 0; i < clients.size();i++){
			clients.get(i).sendMessage("/nn " + id + " "+ clients.get(id).getUName());
		}
	}
	public String getClientName(int id){
		return clients.get(id).getUName();
	}
	public int getCleintLe(){return clients.size();}
	public void guiUpdate(){sg.update();}
	
	public static void main(String[] args){
		new Server_Controller();
	}
}
