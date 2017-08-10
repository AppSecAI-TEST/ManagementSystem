import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MemberInfo extends JPanel {
	private JPanel totalPanel = new JPanel(new GridLayout(3, 1));
	private JLabel la1 = new JLabel("test1~~~!!!!!!!!!!!!!!!!!!");
	private JLabel la2 = new JLabel("test2~~~!!!!!!!!!!!!!!!!!!");
	private JLabel la3 = new JLabel("test3~~~!!!!!!!!!!!!!!!!!!");
	public void compInit(){
		totalPanel.setPreferredSize(new Dimension(500, 500));
		totalPanel.add(la1);
		totalPanel.add(la2);
		totalPanel.add(la3);
		this.add(totalPanel);
		
	}
	public MemberInfo(){
		
		compInit();
	}

}
