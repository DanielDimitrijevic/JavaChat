package Listeners;


import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;

import Interfaces.Controller;
/**
 * Listener der Aktiviert wird wenn das Fenster geschlossen wird
 * @author Dominik
 *
 */
public class WindowListener extends ShellAdapter{
	private Controller c;
	/**
	 * Konstruktor
	 * @param c
	 */
	public WindowListener(Controller c){
		this.c = c;
	}
	/**
	 * Wird aktiv wenn das Fenster geschlossen wird
	 */
	public void shellClosed(ShellEvent e) {
		//System.out.println("Exit!");
		c.disconect();
		System.exit(0);
	}
}
