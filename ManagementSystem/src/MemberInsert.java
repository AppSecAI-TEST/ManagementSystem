import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Position;

public class MemberInsert extends JFrame{
	
	ManagementUtil ut = new ManagementUtil();
	DBManager db = new DBManager();
	
	private JTextField id = new JTextField();
	private JTextField pw = new JTextField();
	private JTextField name = new JTextField();
	private JTextField birth = new JTextField();
	private JTextField date = new JTextField();
	private JTextField comph = new JTextField();
	private JTextField email = new JTextField();
	private JTextField cellphone = new JTextField();
	private JTextField address = new JTextField();
	private JTextField zipcode = new JTextField();
	private JTextField department = new JTextField();
	private JTextField rank = new JTextField();
	
	private JPanel panel = new JPanel(new GridLayout(13, 1));
	
	private JButton submitMember = new JButton("∞°¿‘");
	
	private void compInit(){
		this.panel.add(id);
		this.panel.add(pw);
		this.panel.add(name);
		this.panel.add(birth);
		this.panel.add(date);
		this.panel.add(comph);
		this.panel.add(email);
		this.panel.add(cellphone);
		this.panel.add(address);
		this.panel.add(zipcode);
		this.panel.add(department);
		this.panel.add(rank);
		
		this.panel.add(submitMember);
		
		this.add(panel);
	}
	
	private void eventInit(){
		submitMember.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					db.insertData(new Member(id.getText(), pw.getText(), name.getText(), email.getText(), (Date) ut.string2date(birth.getText()), Integer.parseInt(comph.getText()), Integer.parseInt(cellphone.getText()), address.getText(), Integer.parseInt(zipcode.getText()), department.getText(), "a", rank.getText()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public MemberInsert() {
		this.setSize(200,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MemberInsert();
	}
}
