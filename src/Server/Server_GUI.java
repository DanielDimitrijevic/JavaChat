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
		clientlist.setPreferredSize(new Dimension(30, 30));
		this.add(new JScrollPane(clientlist),BorderLayout.WEST);
		
		
	}
	
	public void addMessage(String msg, String from){
		chat.setText(chat.getText() + from + ": " + msg + "\n");
	}
	public void addUser(String name){
		listText.add(name);
		this.repaint();
	}
	
	public void remUser(String name){
		listText.remove(name);
	}
	public void update(){
	}
}
