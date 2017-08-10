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
class Email3 extends JPanel {
	Email3 self = this;
	CardLayout ca;
	JLabel subject = new JLabel("昏力等皋老", JLabel.LEFT);
	private JPanel panel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JButton button = new JButton("昏力");
	String header[] = { "No.", "力格", "朝楼"};
	Object contents[][] = { { "1", "力格1", "0000-00-00"}, { "2", "力格2", "0000-00-00"} };
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
		subject.setFont(new Font("HY斑绊雕", Font.BOLD, 20));
		this.add(subject,BorderLayout.NORTH);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		centerPanel.setLayout(new BorderLayout(3, 5));
		this.table.getTableHeader().setResizingAllowed(false);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.model.addRow(contents[0]);
		this.model.addRow(contents[1]);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(10);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(300);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(100);
		this.scroll = new JScrollPane(table);
		centerPanel.add(scroll, BorderLayout.CENTER);
		this.southPanel.add(button);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
	}

	public Email3() {
		this.setSize(800, 700);
		this.compInit();
		this.setVisible(true);
	}
}