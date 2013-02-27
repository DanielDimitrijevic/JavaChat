package Multicast_Client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JPanel{
	private Controller c;
	private JTextArea chat;
	private JTextField input;
	
	public GUI(Controller c){
		this.c = c;
		
		this.setLayout(new BorderLayout());
		
		chat = new JTextArea();
		chat.setEditable(false);
		this.add(chat, BorderLayout.CENTER);
		
		input = new JTextField();
		input.addActionListener(new ChatListener());
		this.add(input, BorderLayout.SOUTH);
		
	}
	public void addText(String text){
		chat.setText(chat.getText() + text + "\n");
	}
	
	class ChatListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == input ) {
				String message = input.getText();
				if ( !message.equals("") ) {
					c.sendMessage( message);
					input.setText("");
				}
			}
		}
	}
}
