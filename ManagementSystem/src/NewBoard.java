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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewBoard extends JDialog {
	NewBoard self = this;
	private JLabel label1 = new JLabel("제목");
	private JLabel label2 = new JLabel("작성자");
	private JLabel label3 = new JLabel("내용");
	private JTextField txt1 = new JTextField();
	private JTextField txt2 = new JTextField();
	private JTextField txt3 = new JTextField();
	private JTextArea txtA = new JTextArea(20,40);
	private JButton backB = new JButton("나가기");
	private JButton fileB = new JButton("첨부파일");
	private JButton saveB = new JButton("저장");
	private JPanel panel1 = new JPanel(new GridBagLayout());
	private JPanel panel2 = new JPanel();
	private JPanel areaPanel = new JPanel();
	private JScrollPane scroll;
	private FileDialog fileOpen;

	public void compInit() {
		this.setLayout(new BorderLayout());
		scroll = new JScrollPane(txtA);

		areaPanel.add(scroll, BorderLayout.CENTER);
		label1.setPreferredSize(new Dimension(100, 50));
		label2.setPreferredSize(new Dimension(100, 50));
		label3.setPreferredSize(new Dimension(100, 50));
		txt1.setPreferredSize(new Dimension(400, 25));
		txt2.setPreferredSize(new Dimension(100, 25));
		txt3.setPreferredSize(new Dimension(400, 25));
		fileOpen = new FileDialog(this, "파일열기", FileDialog.LOAD);
		fileB.setPreferredSize(new Dimension(90, 25));

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 20, 0, 0);

		c.gridy = 1;
		c.gridx = 1;
		this.panel1.add(label1, c);
		c.gridy = 1;
		c.gridx = 2;
		this.panel1.add(txt1, c);

		c.gridy = 2;
		c.gridx = 1;
		this.panel1.add(label2, c);
		c.gridy = 2;
		c.gridx = 2;
		this.panel1.add(txt2, c);

		c.gridy = 3;
		c.gridx = 1;
		this.panel1.add(label3, c);
		c.gridy = 3;
		c.gridx = 2;
		this.panel1.add(scroll, c);

		c.gridy = 4;
		c.gridx = 1;
		this.panel1.add(fileB, c);
		c.gridy = 4;
		c.gridx = 2;
		this.panel1.add(txt3, c);

		this.add(panel1, BorderLayout.NORTH);
		panel2.setPreferredSize(new Dimension(400, 30));
		this.panel2.add(saveB, BorderLayout.EAST);
		this.panel2.add(backB, BorderLayout.EAST);
		this.add(panel2, BorderLayout.EAST);
	}

	public void eventInit() {
		this.fileB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileOpen.setDirectory("d:");
				fileOpen.setVisible(true);
				String s = fileOpen.getDirectory() + fileOpen.getFile();
				txt3.setText(s);
			}
		});
		
		this.backB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public NewBoard(Board1 b1) {
		this.setSize(800, 650);
		this.setLocationRelativeTo(b1);
		this.compInit();
		this.eventInit();
		this.setResizable(false);
	}

}