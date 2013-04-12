package Server;

import java.io.IOException;
import java.util.ArrayList;

public class Totengraeber extends Thread{
	private Server_Controller c;
	private ArrayList<Integer> todeleat = new ArrayList<Integer>();
	public Totengraeber(Server_Controller c){
		this.c = c;
	}
	public void add(Integer ID){
		todeleat.add(ID);
	}
	public void run(){
		try{
			while(!this.interrupted()){
				//System.out.println(todeleat.size());
				if(todeleat.size() > 0)
					for(int i= 0; i < c.getCleintLe(); i++)
						if(c.getClients().get(i).getID()==todeleat.get(0)){
							c.getClients().get(i).close();
							c.remove(i);
							todeleat.remove(0);
							c.updateUser();
							c.guiUpdate();
						}
			}
		} catch (IOException e) {
			System.err.println("Konnte die Verbindung nicht trennen: " + e.getMessage());
		}
	}
}
