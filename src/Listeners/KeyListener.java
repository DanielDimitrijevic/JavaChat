package Listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import Interfaces.Controller;
/**
 * Listener schlägt an wenn eine Taste gedrückt wurde
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
	 * Schlägt an wenn eine Taste gedrückt wurde
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		
		if(event.keyCode == SWT.CR){
            System.out.println("Test");
            this.c.keyEvent();
        }
	}
}
