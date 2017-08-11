import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
	
	DBManager db = new DBManager();
	
	Login self = this;
	private JLabel ID = new JLabel("아이디: ");
	private JLabel PassWord = new JLabel("패스워드: ");
	private JTextField Id = new JTextField(10);
	private JPasswordField password = new JPasswordField(10);
	private JButton login = new JButton("로그인");
	public void eventInit(){
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new Home(self).setVisible(true);
				try {
					db.getData();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	public void compInit(){
		login.setBackground(Color.pink);
		
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(-10, 10, 0, 0);

		c.gridy = 1;c.gridx = 1;
		this.add(ID, c);
		c.gridy = 1;c.gridx = 2;
		this.add(Id, c);
		c.gridy = 2;c.gridx = 3;
		this.add(login, c);
		c.gridy = 3;c.gridx = 1;
		this.add(PassWord, c);
		c.gridy = 3;c.gridx = 2;
		this.add(password, c);
	}
	public Login(){
		this.setTitle("로그인");
		this.setSize(300, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Login();
	}
}
