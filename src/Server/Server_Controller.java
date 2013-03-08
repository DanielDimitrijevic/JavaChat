package Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import Frame.MyFrame;
import Interfaces.Controller;
import Listeners.KeyListener;
import Listeners.SelListener;
import Listeners.WindowListener;

public class Server_Controller implements Controller{
	private Server_GUI sg;
	private ArrayList<Server_Conection> clients =new  ArrayList<Server_Conection>();
	private Server_ConectionReader scr;
	private Totengraeber t ;
	private int port;
	private KeyListener kl;
	private SelListener sl;
	private WindowListener wl;
	
	public Server_Controller(int port){
		this.port = port;
		kl = new KeyListener(this);
		sl = new SelListener(this);
		wl = new WindowListener(this);
		sg = new Server_GUI(this,kl,sl,wl);
		scr = new Server_ConectionReader(this,this.port);
		t = new Totengraeber(this);
		t.start();
		sg.open();
	}
	public void addClient(Socket socket){
		System.out.println("Client accepted: " + socket);
		clients.add(new Server_Conection(this,socket));
	}
	
	
	
	public void handle(int id, String txt){
		if(txt.charAt(0) == '/'){
			String [] s =txt.split(" ",2);
			//System.out.println(s[1]);
			if(s[0].charAt(1)== 'n' && s[0].charAt(2)=='n'){
				int tempid = -100;
				boolean acc = true;
				for(int i = 0; i < clients.size();i++){
					if(clients.get(i).getUName().equals(s[1]))
						acc = false;
					if(id == clients.get(i).getID()){
						tempid = i;
					}
				}
				if(acc){
					clients.get(tempid).setUName(s[1]);
					this.signalall(tempid);
					this.guiUpdate();
				}
			}else if(s[0].charAt(1)=='e' && s[0].charAt(2)=='x' && s[0].charAt(3)=='i' && s[0].charAt(4)=='t'){
				this.sendOne("/exit", id);
				t.add(id);
			}
		}else{
			String name ="";
			for(int i = 0; i < clients.size();i++)
				if(clients.get(i).getID()==id)
					if(clients.get(i).getUName().equals("New User"))
						clients.get(i).sendMessage("ERROR: New User hat kein Schreibrecht bitte namen ändern!");
					else
						name = clients.get(i).getUName();
			if(name != ""){
				this.sendAll(txt, name);
				sg.addMessage(txt, name);
			}
		}
	}
	
	
	
	
	public void sendAll(String msg,String name){
		for(int i = 0; i < clients.size();i++){
			clients.get(i).sendMessage(name + ":"+msg);
		}
	}
	public void sendOne(String msg,int id){
		for(int i = 0; i < clients.size();i++){
			if(clients.get(i).getID() == id)
				clients.get(i).sendMessage(msg);
		}
	}
	public void remove(int index){
		clients.remove(index);
	}
	public void removeID(int id){
		for(int i = 0; i < clients.size();i++)
			if(clients.get(i).getID()== id)
				clients.remove(i);
	}
	public void updateUser(){
		for(int i = 0; i < clients.size();i++){
			clients.get(i).sendMessage("/uu uu uu");
		}
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
	public ArrayList<Server_Conection> getClients(){return clients;}
	public void guiUpdate(){
		sg.clearList();
		for(int i = 0; i < clients.size(); i++){
			sg.addUser(i, clients.get(i).getUName());
		}
	}
	public static void main(String[] args){
		boolean a =true;
		if(args.length > 0){
			if(args[0].charAt(0)=='d'){
				a=false;
				new Server_Controller(1234);
			}else { 
				a=false;
				new Server_Controller(Integer.parseInt(args[0]));
				
			}
		}
		if(a)
			System.out.println("No valid Ipaddress or port\n <port> | Serverport angeben \n d | default werte verwenden (1234)");
		
	}
	public void kick(String name){
		for(int i = 0; i < clients.size(); i++){
			if(clients.get(i).getUName() == name){
				this.sendOne("/exit", clients.get(i).getID());
				t.add(clients.get(i).getID());
			}
		}
	}
	@Override
	public void keyEvent() {
		if(sg.getInput().charAt(0)=='/')
			if(sg.getInput().charAt(1)=='k' && sg.getInput().charAt(2)=='i' && sg.getInput().charAt(3)=='c' && sg.getInput().charAt(4)=='k' && sg.getInput().charAt(5)==' ')
				this.kick(sg.getInput().split(" ", 2)[1]);
			else	
				sg.addMessage("Bite keine Befehle eingeben!" ,"Error");
		else{
			this.sendAll(sg.getInput(), "Server");
			sg.addMessage(sg.getInput(), "Server");
		}
		sg.setInput("");
	}
	@Override
	public void selectEvent(int id) {
		switch (id) {
		case 0: {
			if(sg.getInput().charAt(0)=='/')
				if(sg.getInput().charAt(1)=='k' && sg.getInput().charAt(2)=='i' && sg.getInput().charAt(3)=='c' && sg.getInput().charAt(4)=='k' && sg.getInput().charAt(5)==' ')
					this.kick(sg.getInput().split(" ", 2)[1]);
				else	
					sg.addMessage("Bite keine Befehle eingeben!" ,"Error");
			else{
				this.sendAll(sg.getInput(), "Server");
				sg.addMessage(sg.getInput(), "Server");
				sg.setInput("");
			}
		}
		break;
		}
	}
	@Override
	public void disconect() {
		this.sendAll("d", "/exit ");
		for(int i = 0; i < clients.size();i++){
			t.add(clients.get(i).getID());
		}
		scr.stop();
	}
}
