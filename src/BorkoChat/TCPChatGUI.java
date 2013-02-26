package BorkoChat;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.lang.*;
import java.util.*;

public class TCPChatGUI extends JPanel  {

  private static TCPChatCom comChat = null;

  public static JFrame mainFrame = null;
  public static JTextArea chatText = null;
  public static JTextField chatLine = null;
  public static JPanel statusBar = null;
  public static JLabel statusField = null;
  public static JTextField myName = null;
  public static JTextField statusColor = null;
  public static JRadioButton hostOption = null;
  public static JRadioButton guestOption = null;
  public static JButton connectButton = null;
  public static JButton disconnectButton = null;

  ActionListener buttonListener = null;
  ChatListener chatListener = null;

  public TCPChatGUI() {
    super(new BorderLayout());
    chatListener = new ChatListener();
    JPanel pane = null;

    JPanel optionsPane = new JPanel(new GridLayout(3, 1));

    pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    pane.add(new JLabel("My Name:"));
    myName = new JTextField(10); 
    myName.setText("");
    myName.setEnabled(true);
    pane.add( myName );
    optionsPane.add(pane);

    ButtonGroup bg = new ButtonGroup();
    hostOption = new JRadioButton("Server", true);
    hostOption.setMnemonic(KeyEvent.VK_H);
    hostOption.setActionCommand("server");

    guestOption = new JRadioButton("Client", false);
    guestOption.setMnemonic(KeyEvent.VK_G);
    guestOption.setActionCommand("client");

    bg.add(hostOption);
    bg.add(guestOption);
    pane = new JPanel(new GridLayout(1, 2));
    pane.add(hostOption);
    pane.add(guestOption);
    optionsPane.add(pane);

    JPanel buttonPane = new JPanel(new GridLayout(1, 2));

    connectButton = new JButton("Connect");
    connectButton.setMnemonic(KeyEvent.VK_C);
    connectButton.setActionCommand("connect");
    connectButton.addActionListener( chatListener );
    connectButton.setEnabled(true);
    disconnectButton = new JButton("Disconnect");
    disconnectButton.setMnemonic(KeyEvent.VK_D);
    disconnectButton.setActionCommand("disconnect");
    disconnectButton.addActionListener( chatListener );
    disconnectButton.setEnabled(true);
    buttonPane.add(connectButton);
    buttonPane.add(disconnectButton);
    optionsPane.add(buttonPane);

    JPanel chatPane = new JPanel(new BorderLayout());
    chatText = new JTextArea(10, 20);
    chatText.setLineWrap(true);
    chatText.setEditable(false);
    chatText.setForeground(Color.blue);
    JScrollPane chatTextPane = new JScrollPane(chatText,
	JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    chatLine = new JTextField();
    chatLine.setEnabled(true);

    chatLine.addActionListener( chatListener );

    chatPane.add(chatLine, BorderLayout.SOUTH);
    chatPane.add(chatTextPane, BorderLayout.CENTER);
    chatPane.setPreferredSize(new Dimension(200, 250));

    this.add(optionsPane, BorderLayout.SOUTH);
    this.add(chatPane, BorderLayout.NORTH);
  }

  public static void createAndShowGUI( TCPChatCom inComChat ) {
    comChat = inComChat;

    JFrame frame = new JFrame("TCPChat");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JComponent newContentPane = new TCPChatGUI();
    newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);

    frame.pack();
    frame.setVisible(true);
  }

  public static void addMessage( String inMessage ) {
    chatText.append( inMessage + "\n" );
  }

  class ChatListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {

      if ( e.getSource() == chatLine ) {
	String message = chatLine.getText();
	if ( !message.equals("") ) {
	  comChat.sendMessage( myName.getText() + ": " + message + "\n");
	  addMessage( myName.getText() + ": " + message );
	  chatLine.setText("");
	}
      }

      if ( e.getSource() == connectButton ) {
	if ( hostOption.isSelected() ) 
	  comChat.startServer();
	else 
	  comChat.startConnection();
      }

      if ( e.getSource() == disconnectButton ) {
	comChat.stopConnection();
      }       	
    }
  }
}
