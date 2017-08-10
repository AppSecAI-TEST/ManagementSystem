import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewBoard extends JFrame{
	NewBoard self = this;
	private JLabel label1 = new JLabel("제목");
	private JLabel label2 = new JLabel("작성자");
	private JLabel label3 = new JLabel("내용");
	private JTextField txt1 = new JTextField();
	private JTextField txt2 = new JTextField();
	private JTextField txt3 = new JTextField();
	private JTextArea txtA = new JTextArea();
	private JButton btn = new JButton("뒤로가기");
	private JButton btn1 = new JButton("첨부파일");
	private JButton btn2 = new JButton("저장");
	private JPanel panel1 = new JPanel(new GridBagLayout());
	private FileDialog fileOpen;
	
	public void compInit(){
		
		this.setLayout(new BorderLayout());
		GridBagConstraints c =new GridBagConstraints();
		c.insets = new Insets(20,20,0,0);
		
		c.gridy = 1; c.gridx = 1;
		this.panel1.add(label1,c);
		c.gridy = 1; c.gridx = 2;
		this.panel1.add(txt1,c);
		
		c.gridy = 2; c.gridx = 1;
		this.panel1.add(label2,c);
		c.gridy = 2; c.gridx = 2;
		this.panel1.add(txt2,c);
		
		c.gridy = 3; c.gridx = 1;
		this.panel1.add(label3,c);
		c.gridy = 3; c.gridx = 2;
		this.panel1.add(txtA,c);
		
		c.gridy = 4; c.gridx = 1;
		
		this.panel1.add(btn1,c);
		
		c.gridy = 4; c.gridx = 2;
		this.panel1.add(txt3,c);
		
		txtA.setBackground(Color.gray);
		label1.setPreferredSize(new Dimension(100, 50));
		label2.setPreferredSize(new Dimension(100, 50));
		label3.setPreferredSize(new Dimension(100, 50));
		txt1.setPreferredSize(new Dimension(400, 25));
		txt2.setPreferredSize(new Dimension(100, 25));
		txt3.setPreferredSize(new Dimension(400, 25));
		txtA.setPreferredSize(new Dimension(400, 350));
		fileOpen = new FileDialog(this,"파일열기",FileDialog.LOAD);
		btn1.setPreferredSize(new Dimension(90,25));
		this.add(panel1, BorderLayout.NORTH);
	}
	
	public void eventInit(){
		this.btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileOpen.setDirectory("d:");
				fileOpen.setVisible(true);
				String s= fileOpen.getDirectory()+fileOpen.getFile();
				txt3.setText(s);
			}
		});
	}
	
	public NewBoard(){
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new NewBoard();
	}
}