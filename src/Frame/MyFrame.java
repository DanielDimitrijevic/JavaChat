package Frame;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame{
	private JPanel sg;
	
	public MyFrame(JPanel mp, String meinTitel,boolean close){
		sg = mp;
		
		this.setLayout(new BorderLayout());
		this.add(sg);
		this.setTitle(meinTitel);
		this.setBounds(10, 10, 500, 500);
		if(close)
			this.setDefaultCloseOperation(EXIT_ON_CLOSE );
		else
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
