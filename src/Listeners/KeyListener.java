package Listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import Interfaces.Controller;

public class KeyListener extends KeyAdapter{
	private Controller c;
	
	public KeyListener(Controller c){
		this.c = c;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		
		if(event.keyCode == SWT.CR){
            System.out.println("Test");
            this.c.keyEvent();
        }
	}
}
