package Server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server_GUI extends JPanel{
	private JList clientlist;
	private Vector<String> listText;
	private JTextArea chat;
	
	private Server_Controller sc;
	public Server_GUI(Server_Controller sc){
		this.sc = sc;
		
		this.setLayout(new BorderLayout());
		listText = new Vector<String>();
		
		chat = new JTextArea();
		this.add(new JScrollPane(chat) ,BorderLayout.CENTER );
		
		clientlist = new JList(listText);
		clientlist.setPreferredSize(new Dimension(100, 100));
		this.add(new JScrollPane(clientlist),BorderLayout.WEST);
		
		listText.add("Test1");
		listText.add("Test2");
		listText.add("Test3");
		listText.add("Test4");
		listText.add("Test5");
		listText.add("Test6");
		listText.add("Test7");
		listText.add("Test8");
		listText.add("Test9");
		listText.add("Test10");
		listText.add("Test11");
		listText.add("Test12");
		chat.setText("Test \n Test2 \n Test3 ");
		
	}
}
