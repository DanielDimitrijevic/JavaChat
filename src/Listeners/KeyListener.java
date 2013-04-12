package Listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import Interfaces.Controller;
/**
 * Listener schl�gt an wenn eine Taste gedr�ckt wurde
 * @author Dominik, Dimitrijevic
 *
 */
public class KeyListener extends KeyAdapter{
	private Controller c;
	/**
	 * Konstruktor
	 * @param c
	 */
	public KeyListener(Controller c){
		this.c = c;
	}
	/**
	 * Schl�gt an wenn eine Taste gedr�ckt wurde
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		
		if(event.keyCode == SWT.CR){
            System.out.println("Test");
            this.c.keyEvent();
        }
	}
}
