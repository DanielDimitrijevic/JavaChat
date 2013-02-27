package Client;

import Frame.MyFrame;

public class Client_Controller {
	private Client_GUI cg;
	private MyFrame mf;
	private Client_Conection cc;
	
	public Client_Controller(){
		cg = new Client_GUI(this);
		mf = new MyFrame(cg, "Client",true);
	}
	
	public void handle(String msg){
		if(msg.charAt(0) == '/'){
			String [] s =msg.split(" ",3);
			if(s[0].charAt(1)== 'n' && s[0].charAt(2)=='n'){
				int id = Integer.parseInt(s[1]);
				cg.addUser(id,s[2]);
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
		cc.stopCom();
		cc = null;
	}
	public static void main(String[] args){
		new Client_Controller();
	}
}
