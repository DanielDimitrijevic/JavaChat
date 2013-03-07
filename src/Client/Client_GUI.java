package Client;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import Interfaces.Controller;
import Interfaces.GUI;
import Listeners.KeyListener;
import Listeners.SelListener;

import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class Client_GUI  implements GUI{
	private Shell sh;
	private Text input;
	private Text chat;
	private List list;
	
	private Controller c;
	private KeyListener kl;
	private SelListener sl;
	private Text name;
	private Button aendern,send;
	private MenuItem conect,discon;
	
	public Client_GUI(Client_Controller c, KeyListener kl, SelListener sl){
		this.c = c;
		this.kl = kl;
		this.sl = sl;
		Display dis = new Display();
		sh = new Shell(dis);
		sh.setSize(500, 500);
		sh.setLocation(100, 100);
		sh.setText("Client");
		sh.setLayout(new FormLayout());
		
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
		
		MenuItem mntmDatei = new MenuItem(menu, SWT.CASCADE);
		mntmDatei.setText("Datei");
		
		Menu menu_1 = new Menu(mntmDatei);
		mntmDatei.setMenu(menu_1);
		
		MenuItem prop = new MenuItem(menu_1, SWT.NONE);
		prop.setText("Einstellungen");
		prop.addSelectionListener(sl);
		
		MenuItem exit = new MenuItem(menu_1, SWT.NONE);
		exit.setText("Beenden");
		exit.addSelectionListener(sl);
		
		MenuItem mntmVerbindung = new MenuItem(menu, SWT.CASCADE);
		mntmVerbindung.setText("Verbindung");
		
		Menu menu_2 = new Menu(mntmVerbindung);
		mntmVerbindung.setMenu(menu_2);
		
		conect = new MenuItem(menu_2, SWT.NONE);
		conect.setText("Verbinden");
		conect.addSelectionListener(sl);
		
		discon = new MenuItem(menu_2, SWT.NONE);
		discon.setText("Trennen");
		
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
		lblName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblName.setAlignment(SWT.CENTER);
		FormData fd_lblName = new FormData();
		fd_lblName.left = new FormAttachment(0, 10);
		fd_lblName.right = new FormAttachment(0, 79);
		fd_lblName.top = new FormAttachment(0);
		fd_lblName.bottom = new FormAttachment(0, 21);
		lblName.setLayoutData(fd_lblName);
		lblName.setText("Name:");
		discon.addSelectionListener(sl);
		
//		for(int i = 0; i < 100; i++)
//        	list.add("User " + i);
		this.discon();
		sh.open();
		while (!sh.isDisposed ()) {
			if (!dis.readAndDispatch ()) dis.sleep ();
		}
		dis.dispose ();
	}

	@Override
	public String getInput() {
		return input.getText();
	}

	@Override
	public void setInput(String t) {
		input.setText(t);
		
	}
	
	public void addMessage(String msg){
		chat.setText(chat.getText() + msg + "\n");
	}
	
	public void addUser(int id,String name){
		if(list.getItemCount() > id){
			if(list.getItem(id) != name){
				list.setItem(id,name);
			}
		}else{
			list.add(name);
		}
	}
	
	public void remUser(String name){
		list.remove(name);
	}
	public void clearList(){
		list.removeAll();
	}
	
	public void discon(){
		discon.setEnabled(false);
		conect.setEnabled(true);
		name.setEditable(false);
		aendern.setEnabled(false);
		input.setEditable(false);
		send.setEnabled(false);
	}
	public void con(){
		discon.setEnabled(true);
		conect.setEnabled(false);
		name.setEditable(true);
		aendern.setEnabled(true);
		input.setEditable(true);
		send.setEnabled(true);
	}
}
