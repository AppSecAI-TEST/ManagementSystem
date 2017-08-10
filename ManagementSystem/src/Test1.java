import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test1 extends JPanel {
	Test1 self = this;
	private JPanel totalPanel = new JPanel();
	private JLabel Title = new JLabel("New Student");
	private JLabel Stid = new JLabel("Student ID :");
	private JLabel Stname = new JLabel("Student Name :");
	private JTextField ID = new JTextField();
	private JTextField Name = new JTextField();
	private JButton Add = new JButton("Add");
	private JButton Cancle = new JButton("Cancle");
	private JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelNORTH = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel panelCenter = new JPanel(new GridLayout(2, 2));

	private JPanel panelRIGHT = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	private JPanel middle = new JPanel(new GridLayout(3, 1));
	private JLabel empty1 = new JLabel("");
	private JLabel empty2 = new JLabel("");
	private JPanel middle1 = new JPanel(new GridLayout(3, 1));
	private JLabel empty3 = new JLabel("");
	private JLabel empty4 = new JLabel("");
	
	private void compInit() {
		
		panelCenter.add(Stid);

		middle.add(empty1);
		middle.add(ID);
		middle.add(empty2);
		panelCenter.add(middle);

		panelCenter.add(Stname);

		middle1.add(empty3);
		middle1.add(Name);
		middle1.add(empty4);
		panelCenter.add(middle1);

		totalPanel.add(panelCenter);

		Title.setFont(new Font("HY견고딕", Font.BOLD, 20));
		panelNORTH.add(Title);
		totalPanel.add(panelNORTH, BorderLayout.NORTH);
		panelSouth.add(Add);
		Add.setBackground(Color.CYAN);
		Add.setToolTipText("등록하시겠습니까?");
		panelSouth.add(Cancle);
		Cancle.setBackground(Color.WHITE);
		totalPanel.add(panelSouth, BorderLayout.SOUTH);
		this.add(totalPanel);
		}

	
	public boolean IdIntCheck() {
		String ss = ID.getText();
		for (int h = 0; h < ss.length(); h++) {
			if (!(ss.charAt(h) >= '0' && ss.charAt(h) <= '9')) {
				return false;
			}
		}
		return true;
	}

	public Test1() {	
		//this.setSize(600, 500);
		this.compInit();
	}
}