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
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import swing2swt.layout.BorderLayout;
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

public class Client_GUI {
	private Shell sh;
	private Text txtChat;
	private Text text;
	
	
	public Client_GUI(){
		Display dis = new Display();
		sh = new Shell(dis);
		sh.setSize(500, 500);
		sh.setLocation(100, 100);
		sh.setText("Client");
		sh.setLayout(new BorderLayout(0, 0));
		
		Menu menu = new Menu(sh, SWT.BAR);
		sh.setMenuBar(menu);
		
		MenuItem mntmDatei = new MenuItem(menu, SWT.CASCADE);
		mntmDatei.setText("Datei");
		
		Menu menu_1 = new Menu(mntmDatei);
		mntmDatei.setMenu(menu_1);
		
		MenuItem mntmEinstellungen = new MenuItem(menu_1, SWT.NONE);
		mntmEinstellungen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			}
		});
		mntmEinstellungen.setText("Einstellungen");
		
		MenuItem mntmBeenden = new MenuItem(menu_1, SWT.NONE);
		mntmBeenden.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			}
		});
		mntmBeenden.setText("Beenden");
		
		MenuItem mntmVerbundung = new MenuItem(menu, SWT.CASCADE);
		mntmVerbundung.setText("Verbindung");
		
		Menu menu_2 = new Menu(mntmVerbundung);
		mntmVerbundung.setMenu(menu_2);
		
		MenuItem mntmVerbinden = new MenuItem(menu_2, SWT.NONE);
		mntmVerbinden.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			}
		});
		mntmVerbinden.setText("Verbinden");
		
		MenuItem mntmTrennen = new MenuItem(menu_2, SWT.NONE);
		mntmTrennen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			}
		});
		mntmTrennen.setText("Trennen");
		
		Composite composite = new Composite(sh, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new BorderLayout(0, 0));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setLayoutData(BorderLayout.EAST);
		btnNewButton.setText("New Button");
		
		text = new Text(composite, SWT.BORDER);
		
		
		
		
		txtChat = new Text(sh, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		txtChat.setText("Chat");
		txtChat.setLayoutData(BorderLayout.CENTER);
		
		List list = new List(sh, SWT.BORDER | SWT.V_SCROLL);
		list.setItems(new String[] {"User1usaha", "User2", "User3"});
		list.setLayoutData(BorderLayout.EAST);
		
		for(int i = 0; i < 100; i++)
        	list.add("User " + i);
		sh.open();
		while (!sh.isDisposed()) {
			if (!dis.readAndDispatch()) {
				dis.sleep();
			}
        }
	}
}
