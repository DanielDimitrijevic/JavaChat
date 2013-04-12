package Client;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;


import Interfaces.Controller;
import Interfaces.GUI;
import Listeners.KeyListener;
import Listeners.SelListener;
import Listeners.WindowListener;

import org.eclipse.swt.widgets.Label;
//import org.eclipse.wb.swt.SWTResourceManager;
/**
 * Gui für den Client des JavaChats
 * @author Dominik, Dimitrijevic
 *
 */
public class Client_GUI  implements GUI{
	private Shell sh;
	private Text input;
	private Text chat;
	private List list;
	
	private Controller c;
	private KeyListener kl;
	private SelListener sl;
	private WindowListener wl;
	private Text name;
	private Button aendern,send;
	private MenuItem conect,discon;
	private Display dis;
	private String chatt;
	private ArrayList<String> clients = new ArrayList<String>();
	private boolean chata = false, lista = false, disex = false;
	/**
	 * Konstruktor mit Controller und Listener
	 * @param c
	 * @param kl
	 * @param sl
	 * @param wl
	 */
	public Client_GUI(Client_Controller c, KeyListener kl, SelListener sl,WindowListener wl){
		this.c = c;
		this.kl = kl;
		this.sl = sl;
		this.wl = wl;
		
		dis = new Display();
		sh = new Shell(dis);
		sh.setSize(500, 500);
		sh.setLocation(100, 100);
		sh.setText("Client");
		sh.setLayout(new FormLayout());
		sh.addShellListener(wl);
		
		input = new Text(sh, SWT.BORDER);
		input.addKeyListener(kl);
		FormData fd_input = new FormData();
		fd_input.left = new FormAttachment(0);
		input.setLayoutData(fd_input);
		
		chat = new Text(sh, SWT.BORDER | SWT.V_SCROLL);
		FormData fd_chat = new FormData();
		fd_chat.left = new FormAttachment(0);
		fd_chat.bottom = new FormAttachment(input, -8);
		fd_chat.top = new FormAttachment(0, 27);
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
		fd_chat.right = new FormAttachment(list, -6);
		FormData fd_list = new FormData();
		fd_list.left = new FormAttachment(send, 0, SWT.LEFT);
		fd_list.right = new FormAttachment(send, 0, SWT.RIGHT);
		fd_list.bottom = new FormAttachment(send, -6);
		fd_list.top = new FormAttachment(0, 27);
		list.setLayoutData(fd_list);
		
		Menu menu = new Menu(sh, SWT.BAR);
		sh.setMenuBar(menu);
		
		MenuItem mntmVerbindung = new MenuItem(menu, SWT.CASCADE);
		mntmVerbindung.setText("Verbindung");
		
		Menu menu_2 = new Menu(mntmVerbindung);
		mntmVerbindung.setMenu(menu_2);
		
		conect = new MenuItem(menu_2, SWT.NONE);
		conect.setText("Verbinden");
		conect.addSelectionListener(sl);
		
		discon = new MenuItem(menu_2, SWT.NONE);
		discon.setText("Trennen");
		
		MenuItem mntmFarbe = new MenuItem(menu, SWT.CASCADE);
		mntmFarbe.setText("Farbe");
		
		Menu menu_1 = new Menu(mntmFarbe);
		mntmFarbe.setMenu(menu_1);
		
		MenuItem mntmBlau = new MenuItem(menu_1, SWT.NONE);
		mntmBlau.setText("Blau");
		mntmBlau.addSelectionListener(sl);
		
		MenuItem mntmRot = new MenuItem(menu_1, SWT.NONE);
		mntmRot.setText("Rot");
		mntmRot.addSelectionListener(sl);
		
		MenuItem mntmGrn = new MenuItem(menu_1, SWT.NONE);
		mntmGrn.setText("Gr\u00FCn");
		mntmGrn.addSelectionListener(sl);
		
		MenuItem mntmSchwarz = new MenuItem(menu_1, SWT.NONE);
		mntmSchwarz.setText("Schwarz");
		mntmSchwarz.addSelectionListener(sl);
		
		name = new Text(sh, SWT.BORDER);
		FormData fd_name = new FormData();
		fd_name.top = new FormAttachment(chat, -27, SWT.TOP);
		fd_name.bottom = new FormAttachment(chat, -6);
		name.setLayoutData(fd_name);
		
		aendern = new Button(sh, SWT.NONE);
		fd_name.right = new FormAttachment(aendern, -6);
		FormData fd_aendern = new FormData();
		fd_aendern.right = new FormAttachment(send, 0, SWT.RIGHT);
		fd_aendern.top = new FormAttachment(0);
		fd_aendern.left = new FormAttachment(send, 0, SWT.LEFT);
		aendern.setLayoutData(fd_aendern);
		aendern.setText("\u00E4ndern");
		aendern.addSelectionListener(sl);
		
		Label lblName = new Label(sh, SWT.NONE);
		fd_name.left = new FormAttachment(lblName, 6);
		//lblName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblName.setAlignment(SWT.CENTER);
		FormData fd_lblName = new FormData();
		fd_lblName.left = new FormAttachment(0, 10);
		fd_lblName.right = new FormAttachment(0, 79);
		fd_lblName.top = new FormAttachment(0);
		fd_lblName.bottom = new FormAttachment(0, 21);
		lblName.setLayoutData(fd_lblName);
		lblName.setText("Name:");
		discon.addSelectionListener(sl);
		chatt = "";
		
		
		
		
//		for(int i = 0; i < 100; i++)
//        	list.add("User " + i);
		this.discon();
		sh.open();
	}
	/**
	 * Gibt den Inhalt des Eingabefelds zurück
	 */
	@Override
	public String getInput() {
		return input.getText();
	}
	/**
	 * Setzt den Inhalt des Eingabe felds
	 */
	@Override
	public void setInput(String t) {
		input.setText(t);
		
	}
	/**
	 * Fügt eine Nachricht zum Chat fenster hinzu
	 * @param msg
	 */
	public void addMessage(String msg){
		chatt += msg + "\n";
		chata=true;
	}
	/**
	 * Fugt User in Liste Hinzu
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
	 * Löscht User in Liste
	 * @param name
	 */
	public void remUser(String name){
		clients.remove(name);
		lista=true;
	}
	/**
	 * Löscht die Liste
	 */
	public void clearList(){
		clients.clear();
		lista = true;
	}
	
	/**
	 * Trennen der Vebindung
	 */
	public void discon(){
		discon.setEnabled(false);
		conect.setEnabled(true);
		name.setEditable(false);
		aendern.setEnabled(false);
		input.setEditable(false);
		send.setEnabled(false);
		chat.setText("");
		name.setText("New User");
		list.removeAll();
		chatt = "";
		clients.clear();
	}
	/**
	 * Verbundung aufbauen
	 */
	public void con(){
		discon.setEnabled(true);
		conect.setEnabled(false);
		name.setEditable(true);
		aendern.setEnabled(true);
		input.setEditable(true);
		send.setEnabled(true);
	}
	/**
	 * Gibt den INhalt des Namensfeldes Zurück
	 * @return
	 */
	public String getName(){
		return name.getText();
	}
	/**
	 * Setzt Die TextFarbe
	 * @param c
	 */
	public void setColor(Color c){
		chat.setForeground(c);
		input.setForeground(c);
		list.setForeground(c);
		name.setForeground(c);
	}
	public void diconex(){
		disex = true;
	}
	/**
	 * Öffnet das Fenster
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
			if(disex){
				this.discon();
				disex=false;
			}
		}
		dis.dispose ();
	}
}
