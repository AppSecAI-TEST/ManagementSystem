import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//123
//제발!!!????????
class Email1 extends JPanel {
	Email1 self = this;
	CardLayout ca;
	JLabel subject = new JLabel("받은메일함", JLabel.LEFT);
	private JPanel panel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	String header[] = { "No.", "제목", "보낸사람", "날짜", "조회수" };
	Object contents[][] = { { "1", "제목1", "홍길동", "0000-00-00", "001" }, { "2", "제목2", "마이콜", "0000-00-00", "002" } };
	private DefaultTableModel model;
	private JTable table = new JTable();
	private JScrollPane scroll;
	
	
	public void compInit() {
		model = new DefaultTableModel(header, 0){
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new JTable(model);
		setLayout(new BorderLayout());
		subject.setFont(new Font("HY견고딕", Font.BOLD, 20));
		this.add(subject,BorderLayout.NORTH);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		centerPanel.setLayout(new BorderLayout(5, 5));
		this.table.getTableHeader().setResizingAllowed(false);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.model.addRow(contents[0]);
		this.model.addRow(contents[1]);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(50);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(300);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(100);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
		this.scroll = new JScrollPane(table);
		centerPanel.add(scroll, BorderLayout.CENTER);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
	}

	public Email1() {
		this.setSize(800, 700);
		this.compInit();
		this.setVisible(true);
	}
}