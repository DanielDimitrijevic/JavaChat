package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client_GUI extends JPanel{
	private JList clientlist;
	private DefaultListModel<String> listText;
	private JTextArea chat;
	private JTextField name,input;
	private JButton rename, conect, dis;
	
	private Client_Controller cc;
	
	public Client_GUI(Client_Controller cc){
		this.cc = cc;
		
		this.setLayout(new BorderLayout());
		listText = new DefaultListModel<String>();
		
		chat = new JTextArea();
		this.add(new JScrollPane(chat) ,BorderLayout.CENTER );
		
		clientlist = new JList(listText);
		clientlist.setPreferredSize(new Dimension(130, 100));
		this.add(new JScrollPane(clientlist),BorderLayout.WEST);
		
		JPanel p1 = new JPanel(new GridLayout(1,3));
		p1.add(new JLabel("My Name:"));
		name = new JTextField();
		p1.add(name);
		rename = new JButton("ändern");
		p1.add(rename);
		JPanel p2 = new JPanel(new GridLayout(1,2));
		conect = new JButton("Verbinden");
		p2.add(conect);
		dis = new JButton("Trennen");
		p2.add(dis);
		JPanel p3 = new JPanel(new GridLayout(2,1));
		p3.add(p2);
		p3.add(p1);
		this.add(p3, BorderLayout.NORTH);
		
		conect.addActionListener(new ChatListener());
		dis.addActionListener(new ChatListener());
		rename.addActionListener(new ChatListener());
		
		input = new JTextField();
		input.setEnabled(true);
		input.addActionListener(new ChatListener());
		this.add(input,BorderLayout.SOUTH);
		
		name.setText("New User");
		listText.addElement("");
		
		name.setEditable(false);
		rename.setEnabled(false);
		dis.setEnabled(false);
	}
	
	public void addMessage(String msg){
		chat.setText(chat.getText() + msg + "\n");
	}
	
	public void addUser(int id,String name){
		if(listText.size()> id){
			if(listText.get(id) != name){
				listText.set(id,name);
			}
		}else{
			listText.addElement(name);
		}
	}
	
	public void remUser(String name){
		listText.removeElement(name);
	}
	
	class ChatListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == input ) {
				String message = input.getText();
				if ( !message.equals("") ) {
					cc.sendMessage( message);
					input.setText("");
				}
			}
			if ( e.getSource() == conect ){
				cc.conect();
				name.setEditable(true);
				dis.setEnabled(true);
				rename.setEnabled(true);
				conect.setEnabled(false);
			}
			if ( e.getSource() == dis ){
				cc.disconect();
				name.setEditable(false);
				rename.setEnabled(false);
				dis.setEnabled(false);
				conect.setEnabled(true);
			}
			if ( e.getSource() == rename ){
				if(name.getText()!="")
					cc.sendMessage("/nn "+ name.getText());
			}
		}
	}
}
