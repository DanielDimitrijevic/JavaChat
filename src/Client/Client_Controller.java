package Client;


import org.eclipse.swt.graphics.Color;

import Interfaces.Controller;
import Interfaces.GUI;
import Listeners.KeyListener;
import Listeners.SelListener;
import Listeners.WindowListener;

public class Client_Controller implements Controller{
	private Client_GUI cg;
	private Client_Conection cc;
	private KeyListener kl;
	private SelListener sl;
	private WindowListener wl;
	private String addr;
	private int port;
	
	public Client_Controller(String addr , int port){
		this.addr = addr;
		this.port = port;
		kl = new KeyListener(this);
		sl = new SelListener(this);
		wl = new WindowListener(this);
		cg = new Client_GUI(this,kl,sl,wl);
		cg.open();
	}
	
	public void handle(int id, String msg){
		if(msg.charAt(0) == '/'){
			String [] s =msg.split(" ",3);
			if(s[0].charAt(1)== 'n' && s[0].charAt(2)=='n'){
				int id1 = Integer.parseInt(s[1]);
				cg.addUser(id1,s[2]);
			}
			if(s[0].charAt(1)== 'u' && s[0].charAt(2)=='u'){
				cg.clearList();
			}
			if(s[0].charAt(1)=='e' && s[0].charAt(2)=='x' && s[0].charAt(3)=='i' && s[0].charAt(4)=='t'){
				System.out.println("Exit!");
				this.disconecte();
				cg.diconex();
			}
			
		}else{
			cg.addMessage(msg);
		}
	}
	public void sendMessage(String msg){
		cc.sendMessage(msg);
	}
	public void conect(){
		cc = new Client_Conection(addr,port,this);
	}
	public void disconect(){
		if(cc != null)
		cc.sendMessage("/exit Conection");
	}
	public void disconecte(){
		cc.stopCom();
		cc = null;
	}
	public static void main(String[] args){
		boolean a =true;
		if(args.length > 0){
			if(args[0].charAt(0)=='d'){
				a=false;
				new Client_Controller("localhost",1234);
			}else if(args.length >1){ 
				a=false;
				new Client_Controller(args[0],Integer.parseInt(args[1]));
			}
		}
		if(a)
			System.out.println("No valid Ipaddress or port\n <Ipaddress> <port> | Server Addresse und den Serverport angeben \n d | default werte verwenden (localhost:1234)");
		
	}

	@Override
	public void keyEvent() {
		if(cg.getInput().charAt(0)=='/')
			cg.addMessage("Bite keine Befehle eingeben!");
		else{
			cc.sendMessage(cg.getInput());
			cg.setInput("");
		}
	}

	@Override
	public void selectEvent(int id) {
		switch (id) {
		case 0: {
			if(cg.getInput().charAt(0)=='/')
				cg.addMessage("Bite keine Befehle eingeben!");
			else{
				cc.sendMessage(cg.getInput());
				cg.setInput("");
			}
		}
		break;
		case 1: {
			this.conect();
			cg.con();
		}
		break;
		case 2: {
			this.disconect();
			cg.discon();
		}
		break;
		case 3: {
			
		}
		break;
		case 4: {
			this.disconect();
			System.exit(0);
		}
		break;
		case 5: {
			cc.sendMessage("/nn " + cg.getName());
		}
		break;
		case 100: {//Blau
			cg.setColor(new Color(null,0,0,255));
		}
		break;
		case 101: {//Rot
			cg.setColor(new Color(null,255,0,0));
		}
		break;
		case 102: {//Grün
			cg.setColor(new Color(null,0,255,0));
		}
		break;
		case 104: {//Schwarz
			cg.setColor(new Color(null,0,0,0));
		}
		break;
		}
	}
}
