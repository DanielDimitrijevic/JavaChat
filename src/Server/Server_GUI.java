package Server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server_GUI extends JPanel{
	private JList clientlist;
	private DefaultListModel<String> listText;
	private JTextArea chat;
	private Server_Controller sc;
	public Server_GUI(Server_Controller sc){
		this.sc = sc;
		
		this.setLayout(new BorderLayout());
		listText = new DefaultListModel<String>();
		
		chat = new JTextArea();
		chat.setEditable(false);
		this.add(new JScrollPane(chat) ,BorderLayout.CENTER );
		
		clientlist = new JList(listText);
		clientlist.setPreferredSize(new Dimension(130, 100));
		this.add(new JScrollPane(clientlist),BorderLayout.WEST);
		
		listText.addElement("");
	}
	
	public void addMessage(String msg, String from){
		chat.setText(chat.getText() + from + ":" + msg + "\n");
	}
	public void addUser(String name){
		listText.add(listText.size(), name);
		this.repaint();
		clientlist.setSelectedIndex(0);
	}
	
	public void remUser(String name){
		listText.removeElement(name);
	}
	public void update(){
		listText.clear();
		for(int i = 0; i < sc.getCleintLe(); i++){
			listText.add(i,sc.getClientName(i));
		}
		clientlist.setSelectedIndex(0);
	}
}
