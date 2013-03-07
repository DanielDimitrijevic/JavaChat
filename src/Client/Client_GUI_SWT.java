package JavaChat.Client;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Client_GUI_SWT {
	private Shell sh;
	private Client_Controller c;
	private Text input;
	private List liste;
	private FormData fd_input;
	private ScrolledComposite ss1;
	private Text text;
	
	public Client_GUI_SWT(Client_Controller c){
		this.c = c;
		Display dis = new Display();
		sh = new Shell(dis);
		sh.setText("Client");
		
		
		
		sh.setSize(500, 500);
		sh.setLocation(100, 100);
		
		Button send = new Button(sh, SWT.NONE);
		send.setForeground(SWTResourceManager.getColor(0, 0, 0));
		fd_input.right = new FormAttachment(send, -6);
		FormData fd_send = new FormData();
		fd_send.left = new FormAttachment(0, 394);
		fd_send.right = new FormAttachment(100);
		fd_send.bottom = new FormAttachment(100, -1);
		send.setLayoutData(fd_send);
		send.setText("senden");
		
		ScrolledComposite ss = new ScrolledComposite(sh, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_ss = new FormData();
		fd_ss.bottom = new FormAttachment(0, 413);
		fd_ss.right = new FormAttachment(send, 0, SWT.RIGHT);
		fd_ss.top = new FormAttachment(0, 10);
		ss.setLayoutData(fd_ss);
		ss.setExpandHorizontal(true);
		ss.setExpandVertical(true);
		
		
		liste = new List(ss,SWT.BORDER);
		ss.setContent(liste);
		ss.setMinSize(liste.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ss1 = new ScrolledComposite(sh, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_ss1 = new FormData();
		fd_ss1.bottom = new FormAttachment(input, -6);
		fd_ss1.right = new FormAttachment(input, 0, SWT.RIGHT);
		fd_ss1.left = new FormAttachment(0);
		fd_ss1.top = new FormAttachment(0, 10);
		ss1.setLayoutData(fd_ss1);
		ss1.setExpandHorizontal(true);
		ss1.setExpandVertical(true);
		
		text = new Text(ss1, SWT.BORDER);
		ss1.setContent(text);
		ss1.setMinSize(text.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		MyListener.MyListener(text);
		Menu menu = new Menu(sh, SWT.BAR);
		sh.setMenuBar(menu);
		
		MenuItem mntmDatei_1 = new MenuItem(menu, SWT.CASCADE);
		mntmDatei_1.setText("Datei");
		
		Menu menu_1 = new Menu(mntmDatei_1);
		mntmDatei_1.setMenu(menu_1);
		
		MenuItem mntmVerbindung = new MenuItem(menu, SWT.CASCADE);
		mntmVerbindung.setText("Verbindung");
		
		Menu menu_2 = new Menu(mntmVerbindung);
		mntmVerbindung.setMenu(menu_2);
		init();
		
		sh.open();
		
		while (!sh.isDisposed()) {
			if (!dis.readAndDispatch()) {
				dis.sleep();
			}
        }
	}
	public void init(){
		input = new Text(sh, SWT.BORDER);
		
		FormLayout layout = new FormLayout();
        sh.setLayout(layout);

        fd_input = new FormData();
        fd_input.bottom = new FormAttachment(100);
        fd_input.left = new FormAttachment(0);
        fd_input.top = new FormAttachment(95);
        input.setLayoutData(fd_input);
        
        
        for(int i = 0; i < 100; i++)
        	liste.add("User " + i);
	}
}
