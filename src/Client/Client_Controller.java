package Client;

import Frame.MyFrame;

public class Client_Controller {
	private Client_GUI cg;
	private MyFrame mf;
	
	public Client_Controller(){
		cg = new Client_GUI(this);
		mf = new MyFrame(cg, "Client",true);
	}
	
	public static void main(String[] args){
		new Client_Controller();
	}
}
