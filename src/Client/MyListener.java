package JavaChat.Client;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class MyListener {

		public static void MyListener(Text text){
			text.addListener(SWT.KeyDown, new Listener() {
	            public void handleEvent(Event event) {
	                switch (event.keyCode) {
	                case SWT.ESC: {
	                     System.out.println("ESC");
	                }
	                    break;
	                case SWT.CR: {
	                     System.out.println("CR");
	                }
	                    break;
	                }
	            }   
	        });
			
		}
}
