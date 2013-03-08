package Listeners;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import Interfaces.Controller;

public class SelListener extends SelectionAdapter{
	private Controller c;
	public SelListener(Controller c){
		this.c = c;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		//System.out.println(e.toString());
		switch (e.widget.toString()) {
			case "Button {Senden}": {
				c.selectEvent(0);
			}
			break;
			case "MenuItem {Verbinden}": {
				c.selectEvent(1);
			}
			break;
			case "MenuItem {Trennen}": {
				c.selectEvent(2);
			}
			break;
			case "MenuItem {Einstellungen}": {
				c.selectEvent(3);
			}
			break;
			case "MenuItem {Beenden}": {
				c.selectEvent(4);
			}
			break;
			case "Button {ändern}": {
				c.selectEvent(5);
			}
			break;
			case "MenuItem {Blau}": {
				c.selectEvent(100);
			}
			break;
			case "MenuItem {Rot}": {
				c.selectEvent(101);
			}
			break;
			case "MenuItem {Grün}": {
				c.selectEvent(102);
			}
			break;
			case "MenuItem {Gelb}": {
				c.selectEvent(103);
			}
			break;
			case "MenuItem {Schwarz}": {
				c.selectEvent(104);
			}
			break;
		}
	}
}
