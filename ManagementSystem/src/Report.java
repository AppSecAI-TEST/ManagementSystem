import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Report extends JPanel{
	private JLabel titleL = new JLabel("力格");
	private JLabel contentL = new JLabel("郴侩");
	private JLabel fileL = new JLabel("梅何颇老");
	private JTextField titleTF = new JTextField();
	private JTextArea contentTA = new JTextArea();
	private JPanel panelNorth = new JPanel(new GridLayout(1,2));
	private JPanel panelCenter = new JPanel(new GridLayout(1,2));
	private JPanel panelSouth = new JPanel(new GridLayout(1,2));
	
	
	public void compInit(){
		titleL.setPreferredSize(new Dimension(500, 500));
		this.panelNorth.add(titleL);
		this.panelNorth.add(titleTF);
		this.add(this.panelNorth, BorderLayout.NORTH);
		this.panelCenter.add(contentL);
		this.panelCenter.add(contentTA);
		this.contentTA.setBackground(Color.CYAN);
		this.add(this.panelCenter, BorderLayout.CENTER);
		this.panelSouth.add(fileL);
		//this.panelSouth.add(titleTF);
		this.add(this.panelSouth, BorderLayout.SOUTH);
	}
	
	
	public Report(){
		this.setSize(400,500);
		this.compInit();
		//this.eventInit();
		this.setVisible(true);
	}
}
