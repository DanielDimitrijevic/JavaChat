package Server;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

import Interfaces.Controller;
import Interfaces.GUI;
import Listeners.KeyListener;
import Listeners.SelListener;
import Listeners.WindowListener;

/**
 * GUI
 * @author Dominik
 *
 */
public class Server_GUI  implements GUI{
	private Shell sh;
	private Text input;
	private Text chat;
	private List list;
	
	private Controller c;
	private KeyListener kl;
	private SelListener sl;
	private WindowListener wl;
	private Button send;
	private Display dis;
	private String chatt;
	private ArrayList<String> clients = new ArrayList<String>();
	private boolean chata = false, lista = false;
	/**
	 * Konstruktor
	 * @param c
	 * @param kl
	 * @param sl
	 * @param wl
	 */
	public Server_GUI(Server_Controller c, KeyListener kl, SelListener sl,WindowListener wl){
		this.c = c;
		this.kl = kl;
		this.sl = sl;
		
		dis = new Display();
		sh = new Shell(dis);
		sh.addShellListener(wl);
		sh.setSize(500, 500);
		sh.setLocation(100, 100);
		sh.setText("Server");
		sh.setLayout(new FormLayout());
		
		input = new Text(sh, SWT.BORDER);
		input.addKeyListener(kl);
		FormData fd_input = new FormData();
		fd_input.left = new FormAttachment(0);
		input.setLayoutData(fd_input);
		
		chat = new Text(sh, SWT.BORDER | SWT.V_SCROLL);
		chat.setEditable(false);
		FormData fd_chat = new FormData();
		fd_chat.left = new FormAttachment(0);
		fd_chat.bottom = new FormAttachment(input, -8);
		fd_chat.top = new FormAttachment(0);
		chat.setLayoutData(fd_chat);
		
		send = new Button(sh, SWT.NONE);
		send.addSelectionListener(sl);
		fd_input.right = new FormAttachment(send, -6);
		fd_input.top = new FormAttachment(send, 2, SWT.TOP);
		FormData fd_send = new FormData();
		fd_send.left = new FormAttachment(100, -86);
		fd_send.bottom = new FormAttachment(100);
		fd_send.right = new FormAttachment(100);
		send.setLayoutData(fd_send);
		send.setText("Senden");
		
		list = new List(sh, SWT.BORDER | SWT.V_SCROLL);
		fd_chat.right = new FormAttachment(100, -92);
		FormData fd_list = new FormData();
		fd_list.top = new FormAttachment(chat, 0, SWT.TOP);
		fd_list.left = new FormAttachment(chat, 6);
		fd_list.right = new FormAttachment(send, 0, SWT.RIGHT);
		fd_list.bottom = new FormAttachment(send, -6);
		list.setLayoutData(fd_list);
		chatt = "";
		
		
		
		
//		for(int i = 0; i < 100; i++)
//        	list.add("User " + i);
		this.discon();
		sh.open();
	}
	/**
	 * Gibt den Inhalt des EIngabefelds zurück
	 */
	@Override
	public String getInput() {
		return input.getText();
	}
	/**
	 * Setzt den Inhalt des Eingabefelds
	 */
	@Override
	public void setInput(String t) {
		input.setText(t);
		
	}
	/**
	 * Fügt Nachricht den Chat hinzu
	 * @param msg
	 * @param name
	 */
	public void addMessage(String msg,String name){
		chatt +=name + ":" +msg + "\n";
		chata=true;
	}
	/**
	 * User Hinzufügen
	 * @param id
	 * @param name
	 */
	public void addUser(int id,String name){
		if(clients.size() > id){
			if(clients.get(id) != name){
				clients.set(id,name);
			}
		}else{
			clients.add(name);
		}
		lista=true;
	}
	/**
	 * User Löschen
	 * @param name
	 */
	public void remUser(String name){
		clients.remove(name);
		lista=true;
	}
	/**
	 * CLientListe leeren
	 */
	public void clearList(){
		clients.clear();
		lista = true;
	}
	
	/**
	 * Disconect
	 */
	public void discon(){
//		input.setEditable(false);
//		send.setEnabled(false);
		chat.setText("");
		list.removeAll();
		chatt ="";
		clients.clear();
	}
	/**
	 * Öfnet das Fenster
	 */
	public void open() {
		
		while (!sh.isDisposed () ) {
			if (!dis.readAndDispatch ()) dis.sleep ();
			if(lista){
				list.removeAll();
				for(int i = 0; i < clients.size();i++){
					list.add(clients.get(i));
				}
				lista=false;
			}
			if(chata){
				chat.setText(chatt);
				chata=false;
			}
		}
		dis.dispose ();
	}
}
