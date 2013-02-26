package BorkoChat;

/*
 * TCP Chat Version 0.1
 * 
 * Ermöglicht die Kommunikation von 2 Prozessen auf einem Rechner
 */

public class TCPChat {

  public static void main(String args[]) {

    TCPChatCom comChat = new TCPChatCom();
    TCPChatGUI guiChat = new TCPChatGUI();

    guiChat.createAndShowGUI( comChat );
    comChat.setGUI( guiChat );
  }    
}
