import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Email extends JPanel{
	Email self = this;
	CardLayout ca;
	private JPanel panel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private ImageIcon ico;
	private JLabel ReceiveEmail = new JLabel("받는 사람 ");
	private JTextField receiveEmail = new JTextField(59);
	private JLabel TitleL = new JLabel("제목  ");
	private JTextField titleL = new JTextField(59);
	private JButton addressbook = new JButton(new ImageIcon("C:/Users/ITBANK403-15/Pictures/b.jpg"));
	private JLabel Reference = new JLabel("참조  ");
	private JTextField reference = new JTextField(59);
	private JLabel Attached = new JLabel("첨부파일");
	private JButton AttachedFile = new JButton(new ImageIcon("C:/Users/ITBANK403-15/Pictures/c.jpg"));
	private JTextField Attachedfile = new JTextField(59);
	private FileDialog attachedfile;
	private JLabel Text = new JLabel("내용  ");
	private JTextArea text = new JTextArea(17, 59);
	private JScrollPane scroll = new JScrollPane(text);
	private JButton send = new JButton("전송");
	
	//a
	public void compInit() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 5, 0, 5);
		c.gridy = 1;c.gridx = 1;
		this.add(ReceiveEmail, c);
		c.gridy = 1;c.gridx = 2;
		this.add(receiveEmail, c);
		c.gridy = 1;c.gridx = 3;
		this.add(addressbook, c);
		c.gridy = 2;c.gridx = 1;
		this.add(Reference, c);
		c.gridy = 2;c.gridx = 2;
		this.add(reference, c);
		c.gridy = 3;c.gridx = 1;
		this.add(TitleL, c);
		c.gridy = 3;c.gridx = 2;
		this.add(titleL, c);
		c.gridy = 4;c.gridx = 1;
		this.add(Attached, c);
		c.gridy = 4;c.gridx = 2;
		this.add(Attachedfile, c);
		c.gridy = 4;c.gridx = 3;
		this.add(AttachedFile, c);
		c.gridy = 5;c.gridx = 1;
		this.add(Text, c);
		c.gridy = 6;c.gridx = 2;
		this.add(scroll, c);
		c.gridy = 7;c.gridx = 3;
		this.add(send, c);
	}
	public void eventInit(){
	}

	public Email() {
		this.setSize(800, 700);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}

}
