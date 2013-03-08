package Listeners;


import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;

import Interfaces.Controller;

public class WindowListener extends ShellAdapter{
	private Controller c;
	public WindowListener(Controller c){
		this.c = c;
	}
	public void shellClosed(ShellEvent e) {
		//System.out.println("Exit!");
		c.disconect();
		System.exit(0);
	}
}
