package Client;

import Frame.MyFrame;
import Interfaces.Controller;
import Interfaces.GUI;
import Listeners.KeyListener;
import Listeners.SelListener;

public class Client_Controller implements Controller{
	private Client_GUI cg;
	private Client_Conection cc;
	private KeyListener kl;
	private SelListener sl;
	
	public Client_Controller(){
		kl = new KeyListener(this);
		sl = new SelListener(this);
		cg = new Client_GUI(this,kl,sl);
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
			
		}else{
			cg.addMessage(msg);
		}
	}
	public void sendMessage(String msg){
		cc.sendMessage(msg);
	}
	public void conect(){
		cc = new Client_Conection("localhost",1234,this);
	}
	public void disconect(){
		cc.sendMessage("/exit Conection");
		cc.stopCom();
		cc = null;
	}
	public static void main(String[] args){
		new Client_Controller();
	}

	@Override
	public void keyEvent() {
		// TODO Auto-generated method stub
		
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
			//this.conect();
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
		}
	}
}
